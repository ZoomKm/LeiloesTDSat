/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    /**
     * Insere um novo produto na tabela 'produtos' do banco de dados.
     */
    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        conn = new conectaDAO().connectDB();

        try {
            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            new conectaDAO().disconnectDB(conn);
        }

    }

    /**
     * Busca todos os produtos cadastrados no banco de dados.
     *
     * @return Lista dos produtos encontrados no banco de dados.
     */
    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT * FROM produtos";
        listagem.clear();

        conn = new conectaDAO().connectDB();

        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            new conectaDAO().disconnectDB(conn);
        }
        return listagem;
    }

    /**
     * Atualiza o status de um produto para "Vendido" no banco de dados.
     *
     * @param id O identificador único do produto a ser atualizado.
     */
    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        conn = new conectaDAO().connectDB();

        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        } finally {
            new conectaDAO().disconnectDB(conn);
        }
    }

    /**
     * Busca no banco de dados os produtos com status "Vendido".
     *
     * @return Lista de produtos vendidos.
     */
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        listagem.clear();

        conn = new conectaDAO().connectDB();

        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        } finally {
            new conectaDAO().disconnectDB(conn);
        }
        return listagem;
    }
}
