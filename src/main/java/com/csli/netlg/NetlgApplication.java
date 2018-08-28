package com.csli.netlg;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.csli.netlg.domain.Categoria;
import com.csli.netlg.domain.Cidade;
import com.csli.netlg.domain.Cliente;
import com.csli.netlg.domain.Endereco;
import com.csli.netlg.domain.Estado;
import com.csli.netlg.domain.ItemPedido;
import com.csli.netlg.domain.Pagamento;
import com.csli.netlg.domain.PagamentoComBoleto;
import com.csli.netlg.domain.PagamentoComCartao;
import com.csli.netlg.domain.Pedido;
import com.csli.netlg.domain.Produto;
import com.csli.netlg.domain.enums.EstadoPagamento;
import com.csli.netlg.domain.enums.TipoCliente;
import com.csli.netlg.repositories.CategoriaRepository;
import com.csli.netlg.repositories.CidadeRepository;
import com.csli.netlg.repositories.ClienteRepository;
import com.csli.netlg.repositories.EnderecoRepository;
import com.csli.netlg.repositories.EstadoRepository;
import com.csli.netlg.repositories.ItemPedidoRepository;
import com.csli.netlg.repositories.PagamentoRepository;
import com.csli.netlg.repositories.PedidoRepository;
import com.csli.netlg.repositories.ProdutoRepository;

@SpringBootApplication
public class NetlgApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository rCategoria;	
	@Autowired
	private ProdutoRepository rProduto;
	@Autowired
	private CidadeRepository rCidade;
	@Autowired
	private EstadoRepository rEstado;
	@Autowired
	private ClienteRepository rCliente;
	@Autowired
	private EnderecoRepository rEndereco;
	@Autowired
	private PedidoRepository rPedido;
	@Autowired
	private PagamentoRepository rPagamento;
	@Autowired
	private ItemPedidoRepository rItemPedido;
	
	
	/**URL banco HD2: http://localhost:8080/h2-console/login.jsp?jsessionid=9df1fa988dac1214225c9ba37f2f6269 */
	
	public static void main(String[] args) {
		SpringApplication.run(NetlgApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().addAll(Arrays.asList(p2));	
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		rCategoria.saveAll(Arrays.asList(c1, c2));
		rProduto.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "São Pauo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		rEstado.saveAll(Arrays.asList(est1, est2));
		rCidade.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Celso Gomes", "celsosli@gmail.com", "12345678912345", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("32574848", "988724011"));
		
		Endereco end1 = new Endereco(null, "Rua H", "300", "Casa 313", "Rosa Elze", "49100000", cli1, cid1);
		Endereco end2 = new Endereco(null, "Rua Manoel", "1201", "Casa 313", "Madre ", "49100000", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		rCliente.saveAll(Arrays.asList(cli1));
		rEndereco.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("01/01/2018 10:30"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("20/08/2018 12:00"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
			
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PEDENTE, ped2, sdf.parse("20/01/2018 15:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		rPedido.saveAll(Arrays.asList(ped1, ped2));
		rPagamento.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		rItemPedido.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
	}
}
