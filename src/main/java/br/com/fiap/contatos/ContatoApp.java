package br.com.fiap.contatos;
import br.com.fiap.contatos.dao.Conexao;
import br.com.fiap.contatos.dao.ContatoDao;
import br.com.fiap.contatos.dao.TipoContatoDao;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.model.TipoContato;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class ContatoApp {

    public static void main(String[] args) {

        //Criação do EntityManager
        EntityManager em = Conexao.getEntityManeger();


        //cadastrar(em);
        //atualizar(em);
        //excluir(em);
        //consultarContatoPorId(em);
        //listarTodosOsContatos(em);
        //listarContatosPorEmail(em);
        consultaTipoContatoPeloId(em);

    }

    private static void consultaTipoContatoPeloId(EntityManager em) {
        TipoContatoDao tipoContatoDao = new TipoContatoDao(em);
        TipoContato tipoContatoBuscado = new TipoContato();
        tipoContatoBuscado.setId(5L);

        TipoContato tipoContatoEncontrado = new TipoContato();

        tipoContatoEncontrado = tipoContatoDao.buscarTipoContatoPeloId(tipoContatoBuscado);

        System.out.println(tipoContatoEncontrado.getTipo());
        System.out.println(tipoContatoEncontrado.getContatos());
    }

    public static void listarContatosPorEmail(EntityManager em){

        //Criar uma instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);

        List<Contato> contatos = contatoDao.listarContatosPorEmail("kauemluiz@gmail.com");

        for (Contato contato : contatos){
            System.out.println("-----------------------");
            System.out.println(contato.toString());
        }

        System.out.println("Fim dos registros!");
    }




    public static void listarTodosOsContatos(EntityManager em){

        //Criar uma instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);

        List<Contato> contatos = contatoDao.listarTodosOsContatos();

        for (Contato contato : contatos){
            System.out.println("-----------------------");
            System.out.println(contato.toString());
        }

        System.out.println("Fim dos registros!");
    }

    public static void cadastrar(EntityManager em){

        TipoContato tipoContato = new TipoContato();
        //tipoContato.setId(2L);
        tipoContato.setTipo("Trabalho");

        TipoContatoDao tipoContatoDao = new TipoContatoDao(em);

        em.getTransaction().begin();
        tipoContatoDao.salvar(tipoContato);

        Contato contato = new Contato();
        contato.setNome("Pedro");
        contato.setEmail("pedro@gmail.com");
        contato.setDataNascimento(LocalDate.of(2006,8,23));
        contato.setTipoContato(tipoContato);



        //Criar uma instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);

        contatoDao.salvar(contato);
        em.getTransaction().commit();
    }

    public static void atualizar(EntityManager em){
        Contato contato = new Contato();
        contato.setId(4L);
        contato.setNome("Karenzocas");
        contato.setEmail("kauemluiz@gmail.com");
        contato.setDataNascimento(LocalDate.of(2004,7,9));



        //Criar uma instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.atualizar(contato);
        em.getTransaction().commit();
    }

    public static void excluir(EntityManager em){
        Contato contato = new Contato();
        contato.setId(3L);


        //Criar uma instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.excluir(contato);
        em.getTransaction().commit();
    }

    public static void consultarContatoPorId(EntityManager em){


        //Criar uma instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.consultarContatoPorId(2L);
        em.getTransaction().commit();
    }



}
