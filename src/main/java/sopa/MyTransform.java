package sopa;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import soot.util.Chain;
import soot.*;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class MyTransform extends SceneTransformer {
    static public Map<String,SootClass> Classes = new HashMap<>();
    @Override
    protected void internalTransform(String arg0, Map<String, String> arg1) {

        System.out.println("Get Program Structure: \n++++++++++");

        try {
            PrintStream ps = new PrintStream(
                    new FileOutputStream("jimple.txt"));
            SootClass mainClass = Scene.v().getMainClass();
            ps.println("\tMain class is "+ mainClass);
            for (SootMethod m: mainClass.getMethods()) {
                ps.println("\t\tMethod "+ m);
                if (m.hasActiveBody())
                    for (Unit u: m.getActiveBody().getUnits()) {
                        ps.println("\t\t\tUnit " + u);
                    }
            }
            for (SootField f: mainClass.getFields()) {
                ps.println("\t\tField "+f);
            }
            SootMethod mainMethod = Scene.v().getMainMethod();
            ps.println("\tSuper class is "+mainClass.getSuperclass());
            Chain<SootClass> CClass=Scene.v().getClasses();
            for(SootClass cl:CClass){
                Classes.put(cl.toString(),cl);
            }
            ps.println("Classes:");
            ps.println(Classes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SootMethod m =  Scene.v().getMainMethod();
        UnitGraph mgraph = new ExceptionalUnitGraph(m.getActiveBody());
        try {
            Algorithm x = new Algorithm(mgraph);
            x.print();
        }
        catch (Exception e) {
            e.printStackTrace();
            PessiAlg handler = new PessiAlg(Scene.v().getMainClass());
            handler.print();
        }

    }

}
