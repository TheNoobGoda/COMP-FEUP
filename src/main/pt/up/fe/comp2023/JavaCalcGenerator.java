package pt.up.fe.comp2023;

import pt.up.fe.comp.jmm.ast.AJmmVisitor;
import pt.up.fe.comp.jmm.ast.JmmNode;

public class JavaCalcGenerator extends AJmmVisitor<String,String>{
    private String className;

    public JavaCalcGenerator(String className){
        this.className = className;
    }

    protected void buildVisitor() {
        addVisit("Program", this::dealWithProgram);
        addVisit("Assignment", this::dealWithAssignment);
        addVisit("Integer", this::dealWithLiteral);
        addVisit("Identifier", this::dealWithLiteral);
        addVisit("ExprStmt", this::dealWithExprStmt);
        addVisit("BinaryOp",this::dealwithBinaryOp);
    }

    private String dealWithProgram(JmmNode jmmNode,String s){
        s = (s!=null?s:"");
        String ret = s+"public class "+this.className+"{\n";
        String s2 = s+"\t";
        ret += s2+"public static void main(String[] args) {\n";

        for (JmmNode child: jmmNode.getChildren()){
            ret += visit(child,s2+"\t");
            ret += "\n";
        }

        ret += s2 +"}\n";
        ret += s + "}\n";
        return ret;
    }

    private String dealWithAssignment(JmmNode jmmNode,String s){
        return s+"int "+jmmNode.get("var")+ " = "+jmmNode.get("value")+";";
    }

    private String dealWithLiteral(JmmNode jmmNode,String s){
        return s+jmmNode.get("value");
    }

    private String dealWithExprStmt(JmmNode jmmNode, String s){
        String ret = "System.out.println(";
        for (JmmNode child: jmmNode.getChildren()){
            ret += visit(child,"");
        }
        ret+=")";
        return s+ret;
    }

    private String dealwithBinaryOp(JmmNode jmmNode, String s){
        String ret = "";
        int i =0;
        for (JmmNode child: jmmNode.getChildren()){
            ret += visit(child,"");
            if (i==0){
                ret+=" "+ jmmNode.get("op").charAt(1) +" ";
            }
            i++;
        }
        return s+ret;
    }
}
