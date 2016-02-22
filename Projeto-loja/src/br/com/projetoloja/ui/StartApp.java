package br.com.projetoloja.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by João Henrique on 11/01/2016.
 */
public class StartApp extends Application{

    private static StartApp instance;
    private static Pane root;
    private static Stage s;
    public static Stage stageLogin;
    public static Stage stageHome;
    public static Stage stageGerirCliente;
    public static Stage stageGerirProduto;
    public static Stage stageGerenciar;
    public static Stage stageAjuda;
    public static Stage stageNovoProduto;
    public static Stage stageNovoCliente;
    public static Stage stagefinalizarCompra;
    public static Stage stageMaisdetalhesProduto;
    public static Stage stageMaisdetalhesCliente;
    public static Stage stageEditarProduto;
    public static Stage stageEditarCliente;
    public static Stage stageFinalizarPagamentoCarner;
    public static Stage stagePagamentoParcela;
    public static Stage stagePagamentoLivre;
    public static Stage stageTrocarPeca;
    public static Stage stageTrocarPecaCliente;
    public static Stage stageGerarCarner;

    public static StartApp getInstance(){
        if(StartApp.instance == null){
            StartApp.instance = new StartApp();
        }
        return StartApp.instance;
    }

    public static void main (String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
       s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageLogin = s;
    }

    public void home(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaHome.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageHome = s;
    }

    public void telaCliente(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaCliente.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageGerirCliente = s;
    }

    public void telaProduto(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageGerirProduto = s;
    }

    public void telaNovoCliente(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaNovoCliente.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageNovoCliente = s;
    }

    public void telaAjuda(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaAjuda.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageAjuda = s;
    }

    public void telaNovoProduto(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaNovoProduto.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageNovoProduto = s;
    }

    public void telaFinalizarCompra(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaFinalizarCompra.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stagefinalizarCompra = s;
    }

    public void gerenciar(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TelaGerenciar.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageGerenciar = s;
    }

    public void maisDetalhesProduto(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaMaisDetalhesProduto.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageMaisdetalhesProduto = s;
    }


    public void maisDetalhesCliente(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaMaisDetalhesCliente.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageMaisdetalhesCliente = s;
    }


    public void editarProduto(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaEditarProduto.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageEditarProduto = s;
    }


    public void editarCliente(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaEditarCliente.fxml" +
                ""));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageEditarCliente = s;
    }

    public void finalizarPagamentoCarner(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaFinalizarPagamentoCarner.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageFinalizarPagamentoCarner = s;
    }

    public void pagamentoParcela(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaPagamentoParcela.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stagePagamentoParcela = s;
    }

    public void pagamentoParcelaLivre(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaPagamentoLivre.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stagePagamentoLivre = s;
    }

    public void trocarPeca(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaTrocarPeca.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageTrocarPeca = s;
    }
    
    public void trocarPecaCliente(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaTrocarPecaCliente.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageTrocarPecaCliente = s;
    }
    
    public void gerarCarner(Stage primaryStage)throws Exception{
        root = FXMLLoader.load(getClass().getResource("TelaGerarCarner.fxml"));
        s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        Scene sena = new Scene(root,900,600);
        s.setScene(sena);
        s.setResizable(false);
        s.setTitle("Caixa Facil");
        s.show();
        StartApp.stageGerarCarner = s;
    }
    public void closed(){
        s.close();
    }
}
