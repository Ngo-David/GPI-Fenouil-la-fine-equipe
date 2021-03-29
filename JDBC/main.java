package JDBC;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;
public class main {
	public ArrayList<Resultat> requete(String cate, int age, int depart, String carac) throws Exception {
    	 int compte = 0;
    	 String a = null;
    	 String b = null;
    	 String c = null; 
    	 String d = null;
    	 
    	 //creer un requete suivre les donnees
    	 if (cate != null) {
    		a = "WHERE categorie = '" + cate + "'";
    		compte++;
    	 }
    	 if (age != 0) {
    		 if (compte == 0) {
    			 b = "WHERE ROUND(DATEDIFF(CURDATE(), dateNaiss) / 365, 0) = " + String.valueOf(age);
    			 compte++;
    		 } else {
    			 b = "AND ROUND(DATEDIFF(CURDATE(), dateNaiss) / 365, 0) = " + String.valueOf(age);
    			 compte++;
    		 }
    	 }
    	 if (depart != 0) {
    		 if (compte == 0) {
    			 c = "WHERE adresse IN (SELECT idADRESSE FROM ADRESSE WHERE codePostal = " + String.valueOf(depart) +")";
    			 compte++;
    		 } else {
    			 c = "AND adresse IN (SELECT idADRESSE FROM ADRESSE WHERE codePostal = " + String.valueOf(depart) +")";
    			 compte++;
    		 }
    	 }
    	 if (carac != null) {
    		 if (compte == 0) {
    			 d = "WHERE caracteristique = '" + cate + "'";
    			 compte++;
    		 } else {
    			 d = "AND caracteristique = '" + cate + "'";
    			 compte++;
    		 }
    	 }
    	 ArrayList<Resultat> list = new ArrayList<Resultat>();
    	 
    	 //connecter a la BD
    	 String url = "jdbc:oracle:thin:@localhost:3306:fa4a639w_clients";
    	 Connection cnt = DriverManager.getConnection(url,"_iamchrs", "XCLHX#HJCfxB");
    	 
    	 //executer la requete
    	 PreparedStatement stt = cnt.prepareStatement("SELECT idINDIVIDU, nom, prenom, adresse, telephone, mail FROM INDIVIDU " + a + b + c + d);
    	 ResultSet rs = stt.executeQuery();
    	 while (rs.next()) {
    		 Resultat r = new Resultat(rs.getInt("idINDIVIDU"),rs.getString("nom"), rs.getString("prenom"), rs.getInt("adresse"),rs.getString("telephone"), rs.getString("mail"));
    		 list.add(r);
    	 }
    	 stt.close();
    	 rs.close();
    	 cnt.close();
    	 return list;
     }
}
