package tojo.utils.taggedpredicates

trait StructuredTags extends TagConfig {
    trait F
    case class P(l: String) extends F {
        override def toString = l
    }
    case class And(f1: F, f2: F) extends F {
        override def toString = s"($f1 && $f2)"
    }
    case class Or(f1: F, f2: F) extends F {
        override def toString = s"($f1 || $f2)"
    }

    type Label = String
    type Info = F
    def info(label: Label) = P(label)
    def con(i1: Info, i2: Info): Info = And(i1, i2)
    def dis(i1: Info, i2: Info): Info = Or(i1, i2)
}

object LL extends TaggedPredicates with StructuredTags
import LL._

object Test extends App {

    val e = Pred("A", false) || Pred("B", false) && Pred("C", true) || Pred("D", false) && Pred("E", false) && Pred("F", false)

    println(eval(e))

}