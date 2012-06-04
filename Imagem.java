class Imagem
{
  int[][] pixels;
  
  // Inicializa matriz de pixels da classe
  Imagem(int[][] matriz)
  {
    pixels = matriz;
  }
  
  
  
  // Devolve largura em pixels da imagem
  int largura()
  {
    if (pixels != null)
      return pixels[0].length;
    else
      return 0;
  }
  
  
  
  // Devolve altura em pixels da imagem
  int altura()
  {
    if (pixels != null)
      return pixels.length;
    else
      return 0;
  }
  
  
  
  double arredondaParaCima(double n)
    // Este método faz o arredondamento para
    // cima, necesário na suavização
  {
    if(n % 1 > 0)
      n -= n % 1;
    n++;
    return n;
  } // double arredondaParaCima(double n)
  
  
  int medianaDoArray (int [] n)
    //faz o bubble sort, deixando os números naturais primeiro e os -1's depois.
  {
    int auxiliar = 0;
    
    for (int i = 0; i < n.length - 1; i++){
      for (int j= i + 1; j < n.length; j++)
      {
        if (n[i] > n[j]) 
        {  
          auxiliar = n[i];
          n[i]= n[j];
          n[j] = auxiliar;
        }
      }  // for (int j= 1; j < n.length; j++)
    } // for (int i = 0; i < n.length - 1; i++){
    
    auxiliar = 0;

    for (int i = 0; i < n.length; i++) {
      if (i != 256)
        auxiliar ++;
    }
    
    int [] med = new int [auxiliar];
    
    if (med.length % 2 == 1)
      // Se houver um número ímpar de valores considerados,
      // o valor central já será a mediana
      return n [med.length / 2];
    else 
      // Para um número par de valores considerados, fazemos
      // a média dos valores centrais para achar a mediana
    {
      double resultado = ((med[med.length / 2 -1] + med[med.length / 2]) / 2);
        // Fazendo a média dos valores centrais
      resultado = arredondaParaCima(resultado); // Arrendondamos o resultado,
                                                // que pode ser quebrado, para
                                                // cima
      return (int) resultado;
    }
  } // int medianaDoArray (int [] n)
  
  
  
  // Suaviza imagem com filtro médio
  void filtroMedio(int tamanho)
  {
    // Trecho para criar uma matriz 'cópia' que auxilia no cálculo da sua-
    // vização da matriz 'pixels'
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
  
  
  
  // Suaviza imagem com filtro mediano
  void filtroMediano(int tamanho)
  {
    // Trecho para criar uma matriz 'cópia' que auxilia no cálculo da sua-
    // vização da matriz 'pixels'
    int[][] copia = new int[this.altura()][this.largura()];
    for (int i = 0; i <= this.altura() - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= this.largura() - 1; j++) {//j corresponde às colunas
        copia[i][j] = this.pixels[i][j];
      }
    }
    
    
    
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
              n [k * tamanho + h] = 256;
            } 
            else 
              n [k * tamanho + h] = copia[i - tamanho/2 + k][j - tamanho/2 + h]; //Soma o valor do pixel[i+k][j+h] em n para depois dividir e obter a média
          }
          
        }// fecha os dois for's (k,h)
        
        
        mediana = medianaDoArray(n);
        
        if (mediana >= 255)
          mediana = 255;
        
        pixels[i][j] = (int) mediana;
        
      } // for (int j = 0; j <= copia[0].length - 1; j++) //j corresponde às colunas
    } // for (int i = 0; i <= copia.length - 1; i++) { //i corresponde às linhas
    
  } //fim do filtroMediano
  
  
  
  // Suaviza imagem com filtro gaussiano
  void filtroGaussiano(double sigma, int tamanho)
  {
    // Para você completar!
  }
  
}