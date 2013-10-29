package tojo.utils.labeledlogic



object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(139); 

    val tree = Pred("A", false) && Pred("B", true) || Pred("C", true) && Pred("D", false);System.out.println("""tree  : <error> = """ + $show(tree ));$skip(16); val res$0 = 

    eval(tree);System.out.println("""res0: <error> = """ + $show(res$0));$skip(64); 


    //

    //

    println("Welcome to the Scala worksheet")}
}
