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
/**  26/04/12                                        **/
/******************************************************/ 


class Imagem
{
  int[][] pixels; // Imagem que será modificada
  
  
  Imagem(int[][] matriz)
    // Inicializa matriz de pixels da classe
  {
    pixels = matriz;
  }
  
  
  /*------------------------------------------------------------------------*/
  
  
  int largura()
    // Devolve largura em pixels da imagem
  {
    if (pixels != null)
      return pixels[0].length;
    else
      return 0;
  } // int largura()
  
  
  /*------------------------------------------------------------------------*/
  
  
  int altura()
    // Devolve altura em pixels da imagem
  {
    if (pixels != null)
      return pixels.length;
    else
      return 0;
  } // int altura()
  
  
  /*------------------------------------------------------------------------*/
  
  
  double arredondaParaCima(double n)
    // Este método faz o arredondamento para
    // cima, necesário na suavização
  {
    if(n % 1 > 0)
      n -= n % 1;
    n++;
    return n;
  } // double arredondaParaCima(double n)
  
  
  /*------------------------------------------------------------------------*/
  
  
  void organizaVetor(int [] n)
    // Método que organiza dados, realizando a organização 
    // dos valores pelo algoritmo 'bubble sort'.
  {
    int auxiliar = 0;
    
    for (int i = 0; i < n.length - 1; i++){
      for (int j = i + 1; j < n.length; j++)
      {
        if (n[i] > n[i+1]) 
        {  
          auxiliar = n[i];
          n[i]= n[j];
          n[j] = auxiliar;
        }
      }  // for (int j= 1; j < n.length; j++)
    } // for (int i = 0; i < n.length - 1; i++)
  } // organizaVetor
    
  
  /*------------------------------------------------------------------------*/
  
  
  int medianaDoArray(int[] n)
    // O método faz o cálculo da mediana de um certo
    // vetor passado - é usado para calcular a mediana
    // de cada região do entorno dos pontos no filtroMediano
  {
    organizaVetor(n);
    
    int auxiliar = 0;

    for (int i = 0; i < n.length; i++) {
      if (i != 256)
        auxiliar ++;
    }
    
    int [] med = new int [auxiliar];
    
    if (med.length % 2 == 1)
      // Se houver um número ímpar de valores considerados,
      // o valor central já será a mediana
      return n[med.length / 2];
    else {
      // Para um número par de valores considerados, fazemos
      // a média dos valores centrais para achar a mediana
      
      // Fazendo a média dos valores centrais:
      double resultado = ((med[med.length / 2 -1] + med[med.length / 2]) / 2);
      resultado = arredondaParaCima(resultado); // Arrendondamos o resultado,
                                                // que pode ser quebrado, para
                                                // cima
      return (int) resultado;
    } // else
  } // int medianaDoArray (int [] n)
  
  
  /*------------------------------------------------------------------------*/  
  
  
  // Suaviza imagem com filtro médio
  void filtroMedio(int tamanho)
  {
    if(tamanho % 2 == 0 || tamanho <= 0)
      throw new ArithmeticException();
    // Valores pares de tamanho de filtro são inválidos
    
    
    /**
     * Trecho para criar uma matriz 'cópia' que auxilia no cálculo da sua-
     * vização da matriz 'pixels'
     */
    int[][] copia = new int[this.altura()][this.largura()];
    for (int i = 0; i <= this.altura() - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= this.largura() - 1; j++) {//j corresponde às colunas
        copia[i][j] = this.pixels[i][j];
      }
    }
    
    double n = 0; // Variável para guardar o novo valor cada pixel.
                  // Ele é um double para permitir o arredondamento para cima na suavização.
    int razao; // Váriável para guardar o número de pixels contabilizados
               // no cálculo da média para a suavização.
    
    // Laço para percorrer a matriz 'copia' 
    for (int i = 0; i <= copia.length - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= copia[0].length - 1; j++) //j corresponde às colunas
      {
        razao = tamanho*tamanho;
        n = 0;
        //if (//i >= tamanho/2 && j >= tamanho/2 && i < this.altura() - tamanho/2 && j < this.largura() - tamanho/2) { //Se estiver no meio
        
        for (int k = i - tamanho/2; k <= i + tamanho/2; k++) {//k corresponde às linhas em volta do pixel[i][j]
          for (int h = j - tamanho/2; h <= j + tamanho/2; h++) //h corresponde às colunas em volta do pixel[i][j]
          {
            // Os pixels fora da imagem (que não existem, na verdade) são ignorados
            // no cálculo da média - esta metodologia evita a exceção 'IndexArrayOutOfBoundsException'
            if(k < 0 || h < 0 || k > copia.length -1 || h > copia[0].length -1) 
            {
              razao--;
              continue;
            } 
              n += copia[k][h]; //Soma o valor do pixel[i+k][j+h] em n para depois dividir e obter a média
          }
        }// fecha os dois for's (k,h)
        
        // Calculando a suavização
        n = arredondaParaCima(n / razao); // Arredondando o valor da soma de valores dos pixels (n)
                                          // pela razão
        if (n >= 255)
          n = 255;
        
        pixels[i][j] = (int) n;
        
      } // for (int j = 0; j <= copia[0].length - 1; j++) //j corresponde às colunas
    } // for (int i = 0; i <= copia.length - 1; i++) { //i corresponde às linhas
    
  } //fim do método 'filtroMédio'
  
  
  /*------------------------------------------------------------------------*/
  
  
  // Suaviza imagem com filtro mediano
  void filtroMediano(int tamanho)
  {
    if(tamanho % 2 == 0 || tamanho <= 0)
      throw new ArithmeticException();
    // Valores pares de tamanho de filtro são inválidos
    
    
    /**
     * Trecho para criar uma matriz 'cópia' que auxilia no cálculo da sua-
     * vização da matriz 'pixels'
     */
    int[][] copia = new int[this.altura()][this.largura()];
    for (int i = 0; i <= this.altura() - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= this.largura() - 1; j++) {//j corresponde às colunas
        copia[i][j] = this.pixels[i][j];
      }
    } // for
      
    
    int[] n = new int[tamanho*tamanho]; // Vetor para guardar o novo valor cada pixel.
                                        // Ele é um double para permitir que se encontre a mediana.
    int mediana = 0; // Váriável para guardar o valor da mediana
    
    // Laço para percorrer a matriz 'copia' 
    for (int i = 0; i <= copia.length - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= copia[0].length - 1; j++) //j corresponde às colunas
      {
        //if (//i >= tamanho/2 && j >= tamanho/2 && i < this.altura() - tamanho/2 && j < this.largura() - tamanho/2) { //Se estiver no meio
        
        for (int k= 0; k < tamanho; k++) {//k corresponde às linhas em volta do pixel[i][j]       
          for (int h = 0; h < tamanho; h++) //h corresponde às colunas em volta do pixel[i][j]
          {
            // Os pixels fora da imagem (que não existem, na verdade) são ignorados
            // no cálculo da média - esta metodologia evita a exceção 'IndexArrayOutOfBoundsException'
            if(i - tamanho/2 + k < 0 || j - tamanho/2 + h < 0 || i - tamanho/2 + k > copia.length -1 || j - tamanho/2 + h > copia[0].length -1) 
            {
              n[k * tamanho + h] = 256;
            } 
            else 
              n[k * tamanho + h] = copia[i - tamanho/2 + k][j - tamanho/2 + h]; //Soma o valor do pixel[i+k][j+h] em n para depois dividir e obter a média
          }
        }// fecha os dois for's (k,h)
        
        
        mediana = medianaDoArray(n);
        
        if (mediana >= 255)
          mediana = 255;
        
        pixels[i][j] = (int) mediana; // Typecast da mediana, passando de 'double' 
                                      // para 'int'
        
      } // for (int j = 0; j <= copia[0].length - 1; j++) //j corresponde às colunas
    } // for (int i = 0; i <= copia.length - 1; i++) { //i corresponde às linhas
    
  } //fim do filtroMediano

  
  /*------------------------------------------------------------------------*/
  
  
  void filtroGaussiano(double sigma, int tamanho)
    // Suaviza imagem com filtro gaussiano
    // VALOR IDEAL DE TESTE: sigma = 1; tamanho = 3 || 5;
    // melhor teste até o momento: sigma 0.9, tamanho = 3;
  {
    if(tamanho % 2 == 0 || tamanho <= 0)
      throw new ArithmeticException();
    // Valores pares de tamanho de filtro são inválidos
    
    
    /**
     * Trecho para criar uma matriz 'cópia' que auxilia no cálculo da sua-
     * vização da matriz 'pixels'
     */
    int[][] copia = new int[this.altura()][this.largura()];
    for (int i = 0; i <= this.altura() - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= this.largura() - 1; j++) {//j corresponde às colunas
        copia[i][j] = this.pixels[i][j];
      }
    } // for
    
    
    // Laço para percorrer a matriz 'copia' 
    for (int i = 0; i <= copia.length - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= copia[0].length - 1; j++) //j corresponde às colunas
      {
        int n = 0; // Valor do pixel suavizado
        
        for (int k = i - tamanho/2; k <= i + tamanho/2; k++) {//k corresponde às linhas em volta do pixel[i][j]
          for (int h = j - tamanho/2; h <= j + tamanho/2; h++) //h corresponde às colunas em volta do pixel[i][j]
          {
            // Os pixels fora da imagem (que não existem, na verdade) são ignorados
            // no cálculo da função gaussiana - esta metodologia evita a exceção 'IndexArrayOutOfBoundsException'
            if(k < 0 || h < 0 || k > copia.length -1 || h > copia[0].length -1)
              continue;
            
            // Calcula o valor a partir do pixel, baseado na função gaussiana, e depois soma no total
            // que será o pixel suavizado
            n += (Math.exp(-((i-k)*(i-k) + (j-h)*(j-h))/2*sigma*sigma) / (2 * Math.PI * sigma * sigma)) * copia[k][h];
            //System.out.println(n);
          }
        }// fecha os dois for's (k,h)
        
        if (n >= 255) // Ajustando 'n' caso ele ultrapasse 255
          n = 255;
        
        pixels[i][j] = (int) n;
        
      } // for (int j = 0; j <= copia[0].length - 1; j++) //j corresponde às colunas
    } // for (int i = 0; i <= copia.length - 1; i++) { //i corresponde às linhas
    
  } // void filtroGaussiano(double sigma, int tamanho)
  
  
} // class Imagem