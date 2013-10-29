package tojo.utils.labeledlogic

object LL extends LabeledLogic[String] {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(126); 
		def join(l1: LL.L, l2: LL.L): LL.L = s"$l1 || $l2";System.out.println("""join: (l1: tojo.utils.labeledlogic.LL.L, l2: tojo.utils.labeledlogic.LL.L)tojo.utils.labeledlogic.LL.L""")}
		
		
}
import LL._


object Test {

    val tree = Pred("A", false) && Pred("B", true) || Pred("C", true) && Pred("D", false)

    eval(tree)


    //

    //

    println("Welcome to the Scala worksheet")
}
