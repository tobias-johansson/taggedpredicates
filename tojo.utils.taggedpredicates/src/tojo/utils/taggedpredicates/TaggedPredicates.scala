package tojo.utils.taggedpredicates

trait TagConfig {
    type Label
    type Info
    def con(i1: Info, i2: Info): Info
    def dis(i1: Info, i2: Info): Info
    def info(lab: Label): Info
}

abstract class TaggedPredicates[Label] {
	this: TagConfig =>
    
    trait Value
    case class True extends Value
    case class False(info: Info) extends Value

    trait Expr {
        def &&(rhs: Expr): && = TaggedPredicates.this.&&(this, rhs)
        def ||(rhs: Expr): || = TaggedPredicates.this.||(this, rhs)
    }

    class Pred(_label: => Label, _expr: => Boolean) extends Expr {
        lazy val label = _label
        lazy val expr = _expr
    }

    object Pred {
        def apply(label: => Label, expr: => Boolean): Pred = new Pred(label, expr)
        def unapply(pred: Pred): Option[(Label, Boolean)] = Option((pred.label, pred.expr))
    }

    case class &&(lhs: Expr, rhs: Expr) extends Expr

    case class ||(lhs: Expr, rhs: Expr) extends Expr

    def eval(expr: Expr): Value = expr match {
        case lhs && rhs =>
            eval(lhs) match {
                case True()       => eval(rhs)
                case f1 @ False(i1) => eval(rhs) match {
                    case True() => f1
                    case False(i2) => False(con(i1, i2))
                }
            }

        case lhs || rhs =>
            eval(lhs) match {
                case t @ True() => t
                case False(i1) => eval(rhs) match {
                    case t @ True() => t
                    case False(i2)  => False(dis(i1, i2))
                }

            }
        case Pred(label, false) => False(info(label))
        case Pred(_, true)      => True()
    }

}