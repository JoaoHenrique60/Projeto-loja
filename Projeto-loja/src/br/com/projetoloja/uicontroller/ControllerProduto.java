package br.com.projetoloja.uicontroller;

import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.produto.Produto;
import br.com.projetoloja.ui.StartApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João Henrique on 29/12/2015.
 */
public class ControllerProduto {

    List <Produto> lista;

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
    private TableColumn ativoColuna;
    @FXML
    private TextField inputPesquisa;

    @FXML
    private void initialize(){
        try {
            lista = Fachada.getInstance().produtoListarTodos();

            codColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            quantidadeColuna.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            valorColuna.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
            ativoColuna.setCellValueFactory(new PropertyValueFactory<>("ativo"));

            tabela.setItems(FXCollections.observableArrayList(lista));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void pesquisa(){
        try {
            if(isNome()){
               lista = Fachada.getInstance().produtoPesquisarPorNome(inputPesquisa.getText());
            }else{
                Produto p = Fachada.getInstance().produtoPesquisarPorId(Integer.parseInt(inputPesquisa.getText()));
                ArrayList<Produto> listaProduto = new ArrayList<>();
                listaProduto.add(p);
                lista = listaProduto;
            }

            codColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            quantidadeColuna.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            valorColuna.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
            ativoColuna.setCellValueFactory(new PropertyValueFactory<>("ativo"));

            tabela.setItems(FXCollections.observableArrayList(lista));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void novo(){
        try {
            StartApp.stageGerirProduto.close();
            StartApp.getInstance().telaNovoProduto(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ativarDesativar(){
        try {
        Produto p = (Produto) tabela.getSelectionModel().getSelectedItem();
            System.out.println(p.toString());
        if(p.getAtivo() == 'A'){
            Fachada.getInstance().produtoRemover(p.getId());
        }else {
            Fachada.getInstance().produtoAtivar(p.getId());
        }
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void alterar(){
        try {
            Produto p = (Produto) tabela.getSelectionModel().getSelectedItem();
            Produto produto= Fachada.getInstance().produtoPesquisarPorId(p.getId());
            ControllerEditarProduto.produtoEditar = produto;
            StartApp.getInstance().editarProduto(new Stage());
            StartApp.stageGerirProduto.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void maisDetalhes(){
        try {
            Produto p = (Produto) tabela.getSelectionModel().getSelectedItem();
            Produto produto= Fachada.getInstance().produtoPesquisarPorId(p.getId());
            ControllerMaisDetalhesProduto.produtoMaisDetalhes = produto;
            StartApp.getInstance().maisDetalhesProduto(new Stage());
            StartApp.stageGerirProduto.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void trocarPeca(){
        try {
            StartApp.getInstance().trocarPeca(new Stage());
            StartApp.stageGerirProduto.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNome() {
        char [] nome = inputPesquisa.getText().toCharArray();
        boolean isNome = true;
        for(int i = 0; i <= nome.length -1; i++){
            if(nome[i] == '0' ||
                    nome[i] =='1' ||
                    nome[i] =='2' ||
                    nome[i] =='3' ||
                    nome[i] =='4' ||
                    nome[i] =='5' ||
                    nome[i] =='6' ||
                    nome[i] =='7' ||
                    nome[i] =='8' ||
                    nome[i] =='9'){
                    isNome = false;
            }
        }
        return isNome;
    }
}
