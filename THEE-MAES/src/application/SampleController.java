package application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import model.Generatrice;
import model.TypeDistribution;

public class SampleController {
	Alert alert = new Alert(AlertType.WARNING);
	Generatrice gen;
	@FXML
	 NumberAxis yAxis;
	 @FXML
	 CategoryAxis xAxis;

	 @FXML
	 BarChart<String, Number> bc;
	 
	 @FXML
	 ComboBox<Integer> nb_value = new ComboBox<Integer>();
	 
	 @FXML
	 ComboBox<Integer> nb_classe = new ComboBox<Integer>();

	 @FXML
	 TextField param_1 = new TextField();
	 
	 @FXML
	 TextField param_2 = new TextField();
	 
	 @FXML
	 TextField moyenne = new TextField();
	 
	 @FXML
	 TextField ecart = new TextField();
	 
	 @FXML
	 TextField ki2 = new TextField();
	 
	 @FXML
	 Button click_uni = new Button();
	 
	 @FXML
	 Button click_expo = new Button();
	 
	 @FXML
	 Button click_poiss = new Button();
	 
	 @FXML
	 Button click_norm = new Button();
	 
	 @FXML
	 public void initialize(){
	    
		init_nb_value_list();
		init_nb_classe_list();
		xAxis.setLabel("Classes");
	    xAxis.setTickLabelRotation(90);
	    yAxis.setLabel("Densité");

	       
	 }	 
	 private void init_nb_value_list(){
		 
		 ObservableList<Integer> list = FXCollections.observableArrayList(100, 500, 1000, 10000, 50000);
		 nb_value.setItems(list);
		 nb_value.getSelectionModel().select(1);
	 }
	 
	 private void init_nb_classe_list(){
		 ObservableList<Integer> list = FXCollections.observableArrayList(5, 10, 20, 25, 50);
		 nb_classe.setItems(list);
		 nb_classe.getSelectionModel().select(0);
	 }
	 
	 public void onSelectNb(ActionEvent e){
		 if(get_nb_value() == 10){
			 if(get_nb_classe() > 10){
				 display_popup("Le nombre de classes ne peut etre supérieur au nombre de valeurs");
				 nb_classe.getSelectionModel().select(1);
			 }
		 }
	 }
	public void clickOnUni(ActionEvent e){
		clear_chart();
		int nb = get_nb_value();
		int classe = get_nb_classe();
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		if(nb == 0){
			display_popup("Le nombre de valeurs est invalide");
		}
		else{
			gen = new Generatrice(nb, classe, TypeDistribution.UNIFORME);
			gen.getDistribution(null, null);
			double[][] rep_class = gen.repartir_class();
			
			tracer_courbe(rep_class);
		}
		
	}
		
	public void clickOnExpo(ActionEvent e){
		clear_chart();
		int nb = get_nb_value();
		int classe = get_nb_classe();
		double par1 = get_param_1();
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		if(nb == 0){
			display_popup("Le nombre de valeurs est invalide");
		}
		else if(par1 == -1.0){
			display_popup("Le parametre 1 doit avoir une valeur");
		}
		else if(par1 == 0.0){
			display_popup("Le parametre 1 ne peut pas être égal à 0");
		}
		else{
			gen = new Generatrice(nb, classe, TypeDistribution.EXPONENTIELLE);
			gen.getDistribution(par1, null);
			double[][] rep_class = gen.repartir_class();
					    
			tracer_courbe(rep_class);
		}
	}
	
	public void clickOnPoiss(ActionEvent e){
		clear_chart();
		int nb = get_nb_value();
		int classe = get_nb_classe();
		double par1 = get_param_1();
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		if(nb == 0){
			display_popup("Le nombre de valeurs est invalide");
		}
		else if(par1 == -1.0){
			display_popup("Le parametre 1 doit avoir une valeur");
		}
		else{
			gen = new Generatrice(nb, classe, TypeDistribution.POISSON);
			gen.getDistribution(par1, null);
			double[][] rep_class = gen.repartir_class();
			
		    tracer_courbe(rep_class);
		}
	}
	
	public void clickOnNorm(ActionEvent e){
		clear_chart();
		int nb = get_nb_value();
		int classe = get_nb_classe();
		double par1 = get_param_1();
		double par2 = get_param_2();
		
		if(nb == 0){
			display_popup("Le nombre de valeurs est invalide");
		}
		else if(par1 == -1.0){
			display_popup("Le parametre 1 doit avoir une valeur");
		}
		else if(par2 == -1.0){
			display_popup("Le parametre 2 doit avoir une valeur");
		}
		else{
			gen = new Generatrice(nb, classe, TypeDistribution.NORMALE);
			gen.getDistribution(par1, par2);
			double[][] rep_class = gen.repartir_class();
			
			tracer_courbe(rep_class);
		    
		}
	}
	
	public void clickOnKi2(ActionEvent e){
		if(gen != null){
			ki2.setText(gen.test_ki2()+"");
		}
	}
	
	private void tracer_courbe(double[][] rep_class){
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		int diff = 0;
		for(int i = 0; i < rep_class.length; i++){
			if(gen.compte_valeur(rep_class[i]) < 5)
				diff++;
			else
				series1.getData().add(new XYChart.Data((i+1-diff)+"", (gen.compte_valeur(rep_class[i]) )));
			series1.setName(gen.compte_valeur(rep_class[i])+"");
	    }
	    series1.setName("Répartition réel");
	    bc.getData().add(series1);
	    write_moy_ecart();
	}
	private int get_nb_value(){
		if(nb_value.getSelectionModel().getSelectedItem() == null) return 0;
		return Integer.parseInt(nb_value.getSelectionModel().getSelectedItem().toString());
	}
	
	private int get_nb_classe(){
		if(nb_classe.getSelectionModel().getSelectedItem() == null) return 0;
		return Integer.parseInt(nb_classe.getSelectionModel().getSelectedItem().toString());
	}
	
	private double get_param_1(){
		String val = param_1.getText();
		if(val != null && !val.equals("")){
			return Double.parseDouble(val);
		}
		else return -1.0;
	}
	
	private double get_param_2(){
		String val = param_2.getText();
		if(val != null && !val.equals("")){
			return Double.parseDouble(val);
		}
		else return -1.0;
	}
	private void write_moy_ecart(){
		moyenne.setText(new DecimalFormat("#.####").format(gen.moyenne()));
		ecart.setText(new DecimalFormat("#.####").format(gen.variance()));
	}
	
	private void clear_chart(){
		bc.getData().clear();
	}
	private void display_popup(String text){
		alert.setTitle("Attention");
        alert.setHeaderText(text);
        alert.showAndWait(); 
	}
}
