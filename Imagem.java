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
  
  // Suaviza imagem com filtro m�dio
  void filtroMedio(int tamanho)
  {
    
    int[][] copia = new int[this.altura()][this.largura()];
    for (int i = 0; i <= this.altura() - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= this.largura() - 1; j++) {//j corresponde às colunas
        copia[i][j] = this.pixels[i][j];
      }
    }
    
    int n = 0;//Variável para guardar o novo valor cada pixel
    
    for (int i = 0; i <= this.altura() - 1; i++) { //i corresponde às linhas
      for (int j = 0; j <= this.largura() - 1; j++) {//j corresponde às colunas
        
        if (i >= tamanho/2 && j >= tamanho/2 && i < this.altura() - tamanho/2 && j < this.largura() - tamanho/2) { //Se estiver no meio
          
          for (int k = i - tamanho/2; k <= i + tamanho/2; k++) {//k corresponde às linhas em volta do pixel[i][j]
            for (int h = j - tamanho/2; h <= j + tamanho/2; h++) {//h corresponde às colunas em volta do pixel[i][j]
              n += copia[k][h]; //Soma o valor do pixel[i+k][j+h] em n para depois dividir e obter a média
            }
          }// fecha os dois for's (k,h)
          n /= (tamanho * tamanho); //ainda precisa corrigir o arredondamento
          if (n >= 255)
            n = 255;
          pixels[i][j] = n;
          
        }//fecha o if; ainda precisa fazer a suavização nos cantos
        
      }
    }//fecha os dois for's (i,j)
    
  }//fim do método
  
  
  
  // Suaviza imagem com filtro mediano
  void filtroMediano(int tamanho)
  {
    // Para voc� completar!
  }
  
  // Suaviza imagem com filtro gaussiano
  void filtroGaussiano(double sigma, int tamanho)
  {
    // Para voc� completar!
  }
  
}