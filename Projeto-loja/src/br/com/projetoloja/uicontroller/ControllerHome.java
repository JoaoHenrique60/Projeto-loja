package br.com.projetoloja.uicontroller;

import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.produto.Produto;
import br.com.projetoloja.ui.StartApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by João Henrique on 15/01/2016.
 */
public class ControllerHome {

    @FXML
    private TextField codInput;
    @FXML
    private TextField quantidadeInput;
    @FXML
    private TextField nomeProdutoInput;
    @FXML
    private TextField valorInput;
    @FXML
    private TextField valorTotalInput;
    @FXML
    private Button confirmar;
    @FXML
    private TableView tabela;
    @FXML
    private TableColumn codColuna;
    @FXML
    private TableColumn nomeColuna;
    @FXML
    private TableColumn quantidadeColuna;
    @FXML
    private TableColumn valorColuna;
    @FXML
    private TableColumn valorTotalColuna;

    public static double totalPagar = 00;
    public static ArrayList<Produto> listaProdutoFinalizar = new ArrayList<>();
    private List listaProduto;

    @FXML
    private void confimar(KeyEvent event){
        try {
        	if(!codInput.getText().equals("") && !quantidadeInput.getText().equals("")){
            Produto p = Fachada.getInstance().produtoPesquisarPorId(Integer.parseInt(codInput.getText()));
            p.setValorTotal(p.getValorVenda() * Integer.parseInt(quantidadeInput.getText()));
            p.setQuantidade(Integer.parseInt(quantidadeInput.getText()));
            listaProdutoFinalizar.add(p);
            prencherTabela();
            totalPagar += p.getValorTotal();
            valorTotalInput.setText(String.valueOf(totalPagar));
            codInput.clear();
            quantidadeInput.clear();
            nomeProdutoInput.clear();
            valorInput.clear();
            codInput.requestFocus();
        	}else{
        		JOptionPane.showMessageDialog(null, "Preencha todos os campos...");
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void confimarEnter(){

    }

    public void preencherCampos(KeyEvent event){
        try {
            if(event.getCode() == KeyCode.ENTER) {
                Produto p = Fachada.getInstance().produtoPesquisarPorId(Integer.parseInt(codInput.getText()));
                if (p.getQuantidade() > 0) {
                    codInput.setText(String.valueOf(p.getId()));
                    nomeProdutoInput.setText(p.getNome());
                    valorInput.setText(String.valueOf(p.getValorVenda()));
                    quantidadeInput.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null,"Produto fora de estoque " ,
                            "Atenção",JOptionPane.DEFAULT_OPTION);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void finalizarCompra() throws Exception {
        StartApp.getInstance().telaFinalizarCompra(new Stage());
        StartApp.stageHome.close();
    }
    @FXML
    private void cancelar(){
        listaProdutoFinalizar = new ArrayList<>();
        totalPagar = 00;
        prencherTabela();
        codInput.clear();
        quantidadeInput.clear();
        nomeProdutoInput.clear();
        valorInput.clear();
        valorTotalInput.clear();
    }

    @FXML
    private void enterQuantidade(KeyEvent event){
        try{
            if(event.getCode() == KeyCode.ENTER){
            Produto p = Fachada.getInstance().produtoPesquisarPorId(Integer.parseInt(codInput.getText()));
                if(p.getQuantidade() > Integer.parseInt(quantidadeInput.getText())) {
                    confirmar.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null,"Quantidade muito alta\n " +
                            "no estoque tem a pesnas: "+p.getQuantidade()+ " peças");
                }
            }
        }catch (Exception e){

        }
    }

    private void prencherTabela(){
        try {

            listaProduto = listaProdutoFinalizar;
            codColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            quantidadeColuna.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            valorColuna.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
            valorTotalColuna.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

            tabela.setItems(FXCollections.observableArrayList(listaProdutoFinalizar));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
