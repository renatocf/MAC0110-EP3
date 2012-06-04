class ExemploVisualizador
{
  public static void main(String[] args)
  {
    // Cria instância do visualizador
    VisualizadorImagem vis = new VisualizadorImagem();
    
    // Lê imagem
    Imagem barbara = LeituraEscritaImagem.leImagem("Barbara.pgm");
    
    barbara.filtroMediano(7);
    
    // Mostra imagem 
    vis.mostraImagem(barbara, "Barbara.pgm");
    
    // Escreve imagem
    LeituraEscritaImagem.escreveImagem("Barbara_copia.pgm", barbara);
  }
}