/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;
import main.ReadingAgent;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;
import java.io.IOException;
/**
 *
 * @author rajesh
 */
public class CofeeOntologyRead {
    
 
    public static void main(String args[]) throws IOException
            
    {
        sparqlTest();
    }

    static void sparqlTest() throws IOException
    {
        FileManager.get().addLocatorClassLoader(CofeeOntologyRead.class.getClassLoader());
        Model model = FileManager.get().loadModel("/Users/rajesh/Documents/Masters-Edu./AI/project/AIFinalProject/Ontology.rdf");

        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
                "SELECT * WHERE { " +
                " ?price foaf:region_name ?x ." +
                " ?price foaf:value ?y ." +
                "}";
        
         String queryString2 = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
                "SELECT * WHERE { " +
                " ?taste foaf:cof_name ?a ." +
                " ?taste foaf:cof_family ?b ." +
                " ?taste foaf:cof_test ?c ." +
                " ?taste foaf:cof_type ?d ." +
                "}";
         
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            
            ResultSet results = qexec.execSelect();
            while ( results.hasNext() ) {
                QuerySolution soln = results.nextSolution();
                String NeuroData = ReadingAgent.getStroka();
                
             //Price part...handle here..
             Literal region_name = soln.getLiteral("x");
             Literal value = soln.getLiteral("y");
         
             
             if (NeuroData.contains("PRICE") || NeuroData.contains("price") || NeuroData.contains("Price"))   
                System.out.println("Coffee price in "+region_name+" is "+value+"\n");
 
                      
            }
        } finally {
            qexec.close();
        }
                
        Query query2 = QueryFactory.create(queryString2);
        QueryExecution qexec2 = QueryExecutionFactory.create(query2, model);
        try {
            
            ResultSet results = qexec2.execSelect();
            while ( results.hasNext() ) {
                QuerySolution soln = results.nextSolution();
                String NeuroData = ReadingAgent.getStroka();
                
   
            
            //taste part here...   
             Literal cof_name = soln.getLiteral("a"); //Espresso
             Literal cof_family = soln.getLiteral("b");   //WithoutMilk
             Literal cof_test = soln.getLiteral("c");   //Bitter
             Literal cof_type = soln.getLiteral("d");   //HotCoffee
            
             if (NeuroData.contains("Taste") || NeuroData.contains("TASTE") || NeuroData.contains("taste"))
              System.out.println(cof_name+" "+cof_family+" taste "+cof_test+" it could be "+cof_type+"\n");
             
             
            }
        } finally {
            qexec.close();
        }
    }

}