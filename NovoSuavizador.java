import java.util.*;

class NovoVisualizador
{
  public static void main(String[] args)
  {
    boolean fim = false;
    String nome = "a";
    
    
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
      
      
      Scanner sc = new Scanner(System.in);
      // Instanciando um objeto da classe Scanner, para interação
      // com o usuário via teclado.
      
      
      System.out.println("Qual a sua opção?");
      int opcao = sc.nextInt();
      // Permite ao usuário escolher uma opção no menu. A entrada
      // deve ser um int.
      
      
      
      if(opcao == 1) // Opção1: Carregar imagem
      {
        System.out.println("Digite o nome do arquivo:");
        nome = sc.next();
      }
      
      Imagem imagemDoUsuario = LeituraEscritaImagem.leImagem(nome);
      // Cria um objeto da classe 'Imagem' que tem o mesmo endereço de 
      // memória do retorno do método 'LeituraEscritaImagem', o qual
      // é também um objeto da classe 'Imagem' cujo atributo 'pixel'
      // (um vetor de inteiros) armazena as intensidades das cores cor-
      // respondentes a cada pixel da imagem.
      

      String nomeDaCopia;
      // Variável para armazenar o nome do arquivo que, posteriormente, 
      // será criado na pasta da imagem original, armazenando a imagem suavizada.
        
      
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
      

      if(opcao == 2) // Opção2: Visualizar imagem
      {
        VisualizadorImagem vis = new VisualizadorImagem();
        
        if(imagemDoUsuario == null)
          // Checa se a imagem do usuário teve retorno 'null', caso padrão
          // de retorno adotado pela classe 'LeituraEscritaImagem' no método
          // 'leImagem' quando há uma exceção lançada.
          System.out.println("Arquivo não existente ou problema na leitura");
        else
          vis.mostraImagem(imagemDoUsuario, nome);
      }
      
      
      if(opcao == 3) // Opção 3: filtro médio
      {
        boolean entradaCorreta = false;
        
        while(!entradaCorreta) 
        {
          System.out.println("Qual o tamanho da vizinhança desejado?");
          
          try { // Pegando a possível exceção lançada pelo método 'filtro
                // medio', da classe 'Imagem', quando colocados valores pa-
                // res, negativos ou não inteiros como tamanho da vizinhança.
            int tamanho = sc.nextInt();
            imagemDoUsuario.filtroMedio(tamanho);
            
          } catch(ArithmeticException e) {
            System.out.println("Só são aceitos tamanhos ímpares e positivos de vizinhança!");
            continue;
            
          } catch(InputMismatchException e) {
            System.out.println("Só são aceitos valores inteiros!");
            break;
          }
          entradaCorreta = true;
      
        } // while(!entradaCorreta) 
      } // if(opcao == 3)
      
      
      if(opcao == 4) // Opção 4: filtro mediano
      {
        boolean entradaCorreta = false;
        
        while(!entradaCorreta) 
        {
          System.out.println("Qual o tamanho da vizinhança desejado?");
          
          try { // Pegando a possível exceção lançada pelo método 'filtro
                // mediano', da classe 'Imagem', quando colocados valores
                // pares, negativos ou não inteiros como tamanho da vizinhança.
            int tamanho = sc.nextInt();
            imagemDoUsuario.filtroMediano(tamanho);
            
          } catch(ArithmeticException e) {
            System.out.println("Só são aceitos tamanhos ímpares e positivos de vizinhança!");
            continue;
            
          } catch(InputMismatchException e) {
            System.out.println("Só são aceitos valores inteiros!");
            break;
          }
          entradaCorreta = true;
      
        } // while(!entradaCorreta)
      } // if(opcao == 4)
      
      /*----------------------------------------------------*/
      
      if(opcao == 5) // Opção 5: filtro gaussiano
      {      
        boolean entradaCorreta = false;
        int tamanho = 0;
        double sigma = 0;
        
        while(!entradaCorreta) 
        {
          System.out.println("Qual o tamanho da vizinhança desejado?");
          
          try { // Pegando a possível exceção lançada pelo método 'filtro
                // mediano', da classe 'Imagem', quando colocados valores
                // pares, negativos ou não inteiros como tamanho da vizinhança.
            tamanho = sc.nextInt();
            imagemDoUsuario.filtroMediano(tamanho);
            
          } catch(InputMismatchException e) {
            System.out.println("Só são aceitos valores inteiros!");
            break;
          }
          entradaCorreta = true;
        }
         
        
        entradaCorreta = false;
        while(!entradaCorreta) 
        {
          System.out.println("Qual o tamanho desejado para o desvio-padrão?");
          
          try { // Pegando a possível exceção lançada pelo método 'filtro
                // medio', da classe 'Imagem', quando colocados valores pa-
                // res, negativos ou não numéricos como tamanho da vizinhança.
            sigma = sc.nextDouble();
            
          } catch(InputMismatchException e) {
            System.out.println("Só são aceitos valores numéricos!");
            break;
          }
          entradaCorreta = true;
      
        } // while(!entradaCorretaSigma)
        
        
        entradaCorreta = false;
        while(!entradaCorreta) 
        { 
          try { // Pegando a possível exceção lançada pelo método 'filtro
                // medio', da classe 'Imagem', quando colocados valores pa-
                // res, negativos ou não numéricos como tamanho da vizinhança.
            imagemDoUsuario.filtroGaussiano(sigma, tamanho);
            
          } catch(InputMismatchException e) {
            System.out.println("Só são aceitos valores numéricos!");
            break;
          }
          entradaCorreta = true;
        }
      } // if(opcao == 5)
      
      // Mostra imagem 
      //vis.mostraImagem(imagemDoUsuario, "Barbara.pgm");
    
    } // while(!fim)
    
  } // main
} // Class NovoVisualizador