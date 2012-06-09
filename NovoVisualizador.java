/******************************************************/
/**  MAC  110  -  Introdução à  Computação           **/
/**  IME-USP   -  Primeiro  Semestre  de    2012     **/
/**  Turma 45  -  Marcel Parolin Jackowski           **/
/**                                                  **/
/**  Primeiro  Exercício-Programa                    **/
/**  Arquivo:  JogoDeDados.java                      **/
/**                                                  **/
/**  Renato Cordeiro Ferreira            7990933     **/
/**  Vinícius Jorge Vendramini           7991103     **/
/**                                                  **/
/**  26/06/12                                        **/
/******************************************************/ 

import java.util.*;

public class NovoVisualizador
{
  Imagem imagemDoUsuario;
  // Cria um objeto da classe 'Imagem' que tem o mesmo endereço de 
  // memória do retorno do método 'LeituraEscritaImagem', o qual
  // é também um objeto da classe 'Imagem' cujo atributo 'pixel'
  // (um vetor de inteiros) armazena as intensidades das cores cor-
  // respondentes a cada pixel da imagem.
      
  String nome;
  // Variável para armazenar o nome do arquivo original
  
  String nomeDaCopia;
  // Variável para armazenar o nome do arquivo que, posteriormente, 
  // será criado na pasta da imagem original, armazenando a imagem suavizada.
  
  Scanner sc = new Scanner(System.in);
  // Instanciando um objeto da classe Scanner, para interação
  // com o usuário via teclado.
  
  
  
  private int opcao()
  {
    int opcao = 0;
    do 
    { 
      try { opcao = Integer.parseInt(sc.next());
      
      } catch(NumberFormatException e) {
        System.out.println("A opção deve ser um número inteiro!");
        continue;
      }
      
      if(opcao <= 0 || opcao > 8) {
        System.out.println("Só existem 7 opções, de 1 a 7...");
        opcao = 0; continue;
      }  
    } while(opcao == 0);
    
    return opcao;
  } // static int opcao()
  
  
  
  private void lerImagem() // Opção 1
  {
    System.out.println("Digite o nome do arquivo:");
    String nome = sc.next();
    
    if(nome.length() <= 4 || nome.charAt(nome.length()-5) != '.')
      nome = nome + ".pgm";
    
        
    imagemDoUsuario = LeituraEscritaImagem.leImagem(nome);
      
    if(imagemDoUsuario == null)
      // Checa se a imagem do usuário teve retorno 'null', caso padrão
      // de retorno adotado pela classe 'LeituraEscritaImagem' no método
      // 'leImagem' quando há uma exceção lançada.
      System.out.println("Arquivo não existente ou problema na leitura");
    else // Caso tudo tenha ocorrido bem
    {
      nomeDaCopia = nome + "_copia.pgm";
      // Faz o nome do arquivo que, posteriormente, será criado na pas-
      // ta da imagem original, armazenando a imagem suavizada.
      LeituraEscritaImagem.escreveImagem(nomeDaCopia, imagemDoUsuario);
      // Cria um novo arquivo com a cópia, baseada na cópia suavizada.
    }
  } // private void lerImagem(String nome)
  
  
  
  private void visualizarImagem() // Opção 2
  {
    VisualizadorImagem vis = new VisualizadorImagem();
    
    if(imagemDoUsuario == null)
      // Checa se a imagem do usuário teve retorno 'null', caso padrão
      // de retorno adotado pela classe 'LeituraEscritaImagem' no método
      // 'leImagem' quando há uma exceção lançada.
      System.out.println("Arquivo não existente ou problema na leitura");
    else
      vis.mostraImagem(imagemDoUsuario, nome);
  } // private void visualizarImagem()
  
  
  
  private void filtroMedio() // Opção 3
  {    
    System.out.println("Qual o tamanho da vizinhança desejado?");
    String tamanho = sc.next();
    
    try { // Pegando a possível exceção lançada pelo método 'filtro
          // medio', da classe 'Imagem', quando colocados valores pa-
          // res, negativos ou não inteiros como tamanho da vizinhança.
      imagemDoUsuario.filtroMedio(Integer.parseInt(tamanho));
            
    } catch(NumberFormatException e) {
      System.out.println("Só são aceitos números inteiros!");
      filtroMedio();
    
    } catch(ArithmeticException e) {
      System.out.println("Só são aceitos valores ímpares maiores que 0!");
      filtroMedio();
    
    } // try-catch
  } // static private void filtroMedio()
  
  
  
  private void filtroMediano() // Opção 4
  {    
    System.out.println("Qual o tamanho da vizinhança desejado?");
    String tamanho = sc.next();
    
    try { // Pegando a possível exceção lançada pelo método 'filtro
          // mediano', da classe 'Imagem', quando colocados valores pa-
          // res, negativos ou não inteiros como tamanho da vizinhança.
      imagemDoUsuario.filtroMediano(Integer.parseInt(tamanho));
            
    } catch(NumberFormatException e) {
      System.out.println("Só são aceitos números inteiros!");
      filtroMediano();
    
    } catch(ArithmeticException e) {
      System.out.println("Só são aceitos valores ímpares maiores que 0!");
      filtroMediano();
    
    } // try-catch
  } // static private void filtroMediano()
    
  
  
  private void filtroGaussiano() // Opção 5
  {
    int tamanho = 0; double sigma = 0;
    boolean valorSigmaVálido = false, valorTamanhoVálido = false;
        
        
    while(!valorTamanhoVálido)
    {
      System.out.println("Qual o tamanho da vizinhança desejado?");
    
        try {
          tamanho = Integer.parseInt(sc.next());
          if(tamanho % 2 == 1 || tamanho < 0)
            throw new ArithmeticException();
          // Buscando exceções para o tamanho da vizinhança
      
        } catch(NumberFormatException e) {
          System.out.println("Só são aceitos números inteiros!");
          continue;
    
        } catch(ArithmeticException e) {
          System.out.println("Só são aceitos valores ímpares maiores que 0!");
          continue;
    
        } // try-catch: Integer.parseInt(sc.next());
    
        valorTamanhoVálido = true;
    } // while(!valorTamanhoVálido)
    
    
    while(!valorSigmaVálido)
    {
      System.out.println("Qual o desvio-padrão desejado?");
    
      try { 
        sigma = Double.parseDouble(sc.next());
        if(sigma <= 0)
          throw new ArithmeticException();
        // Buscando exceções para o desvio-padrão.
        
      } catch(NumberFormatException e) {
        System.out.println("Só são aceitos números inteiros!");
        continue;
        
      } catch(ArithmeticException e) {
        System.out.println("Só são aceitos valores maiores que 0!");
        continue;
        
      } // try-catch: sigma = Double.parseDouble(sc.next());
      
      valorSigmaVálido = true;
    } // while(!valorSigmaVálido)
    
    imagemDoUsuario.filtroGaussiano(sigma, tamanho);
    // aplicando o Filtro Gaussiano na cópia da imagem.
    
  } // static private void filtroGaussiano()
  
  
  
  private void gravaImagem()
  {
    
  } // private void gravaImagem()
  
  
  
  public static void main(String[] args)
  {
    NovoVisualizador menu = new NovoVisualizador();
    boolean fim = false;
    
    while(!fim)
    {
      System.out.println("Menu");
      System.out.println("====");
      System.out.println("1) Ler imagem");
      System.out.println("2) Visualizar imagem");
      System.out.println("3) Suavizar com filtro médio");
      System.out.println("4) Suavizar com filtro mediano");
      System.out.println("5) Suavizar com filtro gaussiano");
      System.out.println("6) Gravar imagem");
      System.out.println("7) Fim");
      // Cria instância do visualizador
      
      
      System.out.println("Qual a sua opção?");
      int opcao = menu.opcao();
      
      
      if(opcao == 1) // Opção1: Carregar imagem
        menu.lerImagem();
            
      if(opcao == 2) // Opção2: Visualizar imagem
        menu.visualizarImagem();
      
      if(opcao == 3) // Opção 3: filtro médio
        menu.filtroMedio();
      
      if(opcao == 4) // Opção 4: filtro mediano
        menu.filtroMediano();
      
      if(opcao == 5) // Opção 5: filtro gaussiano
        menu.filtroGaussiano();
      
      if(opcao == 6) // Opção 6: gravar imagem
      {
        menu.gravaImagem();
        continue; // ainda não implementada
      }
        
      if(opcao == 7) // Opção 7: fim
        fim = true;
      
    } // while(!fim)
    
  } // main
  
} // class NovoVisualizador