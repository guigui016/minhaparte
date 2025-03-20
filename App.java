import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    // Variáveis para coletar os dados do usuário
    static private String titulo;
    static private String artista;
    static private String album;
    static private String genero;
    static private int anoLancamento;
    static private double duracao;

    // Listas para ajudar na pesquisa
    static private List<Musica> listMusicas = new ArrayList<>();
    static private List<Artista> listArtista = new ArrayList<>();
    static private List<Album> listAlbum = new ArrayList<>();
    static private List<Playlist> listPlaylist = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("Selecione o que você quer fazer:");
            System.out.println("1 - Adicionar Música");
            System.out.println("2 - Realizar buscas");
            System.out.println("3 - Playlists");
            System.out.println("4 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Título:");
                    titulo = scanner.nextLine();

                    System.out.println("Artista:");
                    artista = scanner.nextLine();

                    System.out.println("Álbum:");
                    album = scanner.nextLine();

                    System.out.println("Gênero:");
                    genero = scanner.nextLine();

                    System.out.println("Ano de Lançamento:");
                    anoLancamento = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Duração (em segundos):");
                    duracao = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Caminho do arquivo de áudio:"); // Novo campo
                    String caminhoArquivo = scanner.nextLine();

                    // Cria uma nova instância de música
                    Musica musica = new Musica(titulo, artista, album, genero, anoLancamento, duracao, caminhoArquivo);
                    listMusicas.add(musica);
                    System.out.println("Música adicionada com sucesso!");
                }

                case 2 -> {
                    System.out.println("Selecione o tipo de busca:");
                    System.out.println("1 - Por Título");
                    System.out.println("2 - Por Artista");
                    System.out.println("3 - Por Álbum");
                    System.out.println("4 - Por Gênero");
                    System.out.println("5 - Por Ano de Lançamento");
                    System.out.println("6 - Reproduzir Música"); // Nova opção
                    int opcaoBusca = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o termo de busca:");
                    String termoBusca = scanner.nextLine();

                    List<Musica> resultados = new ArrayList<>();

                    switch (opcaoBusca) {
                        case 1 -> {
                            for (Musica musica : listMusicas) {
                                if (musica.getTitulo().equalsIgnoreCase(termoBusca)) {
                                    resultados.add(musica);
                                }
                            }
                        }
                        case 2 -> {
                            for (Musica musica : listMusicas) {
                                if (musica.getArtista().equalsIgnoreCase(termoBusca)) {
                                    resultados.add(musica);
                                }
                            }
                        }
                        case 3 -> {
                            for (Musica musica : listMusicas) {
                                if (musica.getAlbum().equalsIgnoreCase(termoBusca)) {
                                    resultados.add(musica);
                                }
                            }
                        }
                        case 4 -> {
                            for (Musica musica : listMusicas) {
                                if (musica.getGenero().equalsIgnoreCase(termoBusca)) {
                                    resultados.add(musica);
                                }
                            }
                        }
                        case 5 -> {
                            int anoBusca = Integer.parseInt(termoBusca);
                            for (Musica musica : listMusicas) {
                                if (musica.getAnoLancamento() == anoBusca) {
                                    resultados.add(musica);
                                }
                            }
                        }
                        case 6 -> { // Nova opção para reproduzir música
                            for (Musica musica : listMusicas) {
                                if (musica.getTitulo().equalsIgnoreCase(termoBusca)) {
                                    System.out.println("Reproduzindo: " + musica.getTitulo());
                                    musica.reproduzir();

                                    // Menu de controle de reprodução
                                    int opcaoReproducao;
                                    do {
                                        System.out.println("1 - Pausar");
                                        System.out.println("2 - Retomar");
                                        System.out.println("3 - Parar");
                                        System.out.println("4 - Voltar");
                                        opcaoReproducao = scanner.nextInt();
                                        scanner.nextLine();

                                        switch (opcaoReproducao) {
                                            case 1 -> musica.pausar();
                                            case 2 -> musica.retomar();
                                            case 3 -> musica.parar();
                                            case 4 -> System.out.println("Voltando ao menu principal...");
                                            default -> System.out.println("Opção inválida.");
                                        }
                                    } while (opcaoReproducao != 4);
                                    break;
                                }
                            }
                        }
                        default -> System.out.println("Opção inválida.");
                    }

                    if (resultados.isEmpty() && opcaoBusca != 6) {
                        System.out.println("Nenhuma música encontrada.");
                    } else if (opcaoBusca != 6) {
                        System.out.println("Resultados da busca:");
                        for (Musica musica : resultados) {
                            System.out.println("-------------------");
                            System.out.println(musica.toString());
                        }
                    }
                    System.out.println("-------------------");
                }

                case 3 -> {
                    int opcao2 = 1;
                    while (opcao2 >= 1 && opcao2 <= 4) {
                        System.out.println("Edição de Playlist:");
                        System.out.println("1 - Criar Playlist");
                        System.out.println("2 - Editar Playlists");
                        System.out.println("3 - Excluir Playlist");
                        System.out.println("4 - Visualizar Playlists");
                        System.out.println("5 - Voltar");
                        opcao2 = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        switch (opcao2) {
                            case 1 -> { // Criar Playlist
                                System.out.print("Digite o nome da nova playlist: ");
                                String nomePlay = scanner.nextLine();
                                Playlist playlist = new Playlist(nomePlay);
                                listPlaylist.add(playlist);
                                System.out.println("Playlist '" + nomePlay + "' criada com sucesso!");
                            }
                            case 2 -> { // Editar Playlist
                                if (listPlaylist.isEmpty()) {
                                    System.out.println("Nenhuma playlist criada ainda!");
                                    break;
                                }

                                System.out.println("Suas Playlists:");
                                for (int i = 0; i < listPlaylist.size(); i++) {
                                    System.out.println((i + 1) + ": " + listPlaylist.get(i).getNome());
                                }

                                System.out.print("Escolha a playlist para editar (por número): ");
                                int nPlay = scanner.nextInt();
                                scanner.nextLine();

                                if (nPlay >= 1 && nPlay <= listPlaylist.size()) {
                                    nPlay = nPlay - 1;
                                    Playlist playlist = listPlaylist.get(nPlay);

                                    System.out.println("Selecione uma ação:");
                                    System.out.println("1 - Adicionar música");
                                    System.out.println("2 - Remover música");
                                    System.out.println("3 - Editar ordem da música");
                                    System.out.println("4 - Voltar");

                                    int acao = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (acao) {
                                        case 1 -> {  // Adicionar música
                                            System.out.print("Digite o título da música para adicionar: ");
                                            String musicaAdd = scanner.nextLine();
                                            boolean verify = false;
                                            for (Musica musica : listMusicas) {
                                                if (musica.getTitulo().equals(musicaAdd)) {
                                                    playlist.addMusicPlaylist(musica);
                                                    System.out.println("Música '" + musica.getTitulo() + "' adicionada com sucesso");
                                                    verify = true;
                                                }
                                            }
                                            if (!verify) {
                                                System.out.println("Música não encontrada!");
                                            }
                                        }
                                        case 2 -> { // Remover música
                                            if (playlist.getMusicPlaylist().isEmpty()) {
                                                System.out.println("Não há músicas na Playlist '" + playlist.getNome() + "'");
                                                break;
                                            }
                                            playlist.mostrarMusicas();
                                            System.out.print("Digite o título da música para remover: ");
                                            String musicaRemove = scanner.nextLine();
                                            boolean verify = false;
                                            for (Musica musica : listMusicas) {
                                                if (musica.getTitulo().equals(musicaRemove)) {
                                                    playlist.removeMusicPlaylist(musica);
                                                    System.out.println("A música '" + musica.getTitulo() + "' foi removida com sucesso");
                                                    verify = true;
                                                }
                                            }
                                            if (!verify) {
                                                System.out.println("Música não encontrada!");
                                            }
                                        }
                                        case 3 -> { // Editar posição
                                            if (playlist.getMusicPlaylist().size() <= 1) {
                                                System.out.println("Há apenas uma música na playlist '" + playlist.getNome() + "', logo não é possível mudar a posição");
                                                break;
                                            }
                                            playlist.mostrarMusicasID();
                                            System.out.print("Escolha a posição de origem da música que quer trocar: ");
                                            int posicaoOrigem = scanner.nextInt();
                                            System.out.print("Escolha a posição de destino da música que quer trocar: ");
                                            int posicaoDestino = scanner.nextInt();
                                            playlist.editarPosicaoMusica(posicaoOrigem - 1, posicaoDestino - 1);
                                        }
                                        case 4 -> System.out.println("Voltando...");
                                        default -> System.out.println("Opção inválida");
                                    }
                                } else {
                                    System.out.println("Número da Playlist inválido");
                                }
                            }
                            case 3 -> { // Excluir Playlist
                                if (listPlaylist.isEmpty()) {
                                    System.out.println("Nenhuma playlist criada ainda!");
                                    break;
                                }
                                System.out.println("Suas Playlists:");
                                for (int i = 0; i < listPlaylist.size(); i++) {
                                    System.out.println((i + 1) + ": " + listPlaylist.get(i).getNome());
                                }
                                System.out.println("Selecione o número da Playlist que deseja deletar");
                                int nPlay = scanner.nextInt() - 1;
                                scanner.nextLine();

                                if (nPlay >= 0 && nPlay < listPlaylist.size()) {
                                    Playlist play = listPlaylist.get(nPlay);
                                    play.excluirPlaylist();
                                    listPlaylist.remove(play);
                                    System.out.println("Playlist deletada com sucesso");
                                } else {
                                    System.out.println("Index da playlist inválido");
                                }
                            }
                            case 4 -> { // Visualizar Playlists
                                if (listPlaylist.isEmpty()) {
                                    System.out.println("Nenhuma playlist criada ainda!");
                                    break;
                                }
                                System.out.println("Suas Playlists:");
                                for (int i = 0; i < listPlaylist.size(); i++) {
                                    System.out.println((i + 1) + ": " + listPlaylist.get(i).getNome());
                                }
                                System.out.println("Selecione o número da Playlist que deseja visualizar");
                                int nPlay = scanner.nextInt() - 1;
                                scanner.nextLine();

                                if (nPlay >= 0 && nPlay < listPlaylist.size()) {
                                    Playlist play = listPlaylist.get(nPlay);
                                    play.mostrarMusicas();
                                } else {
                                    System.out.println("Index da playlist inválido");
                                }
                            }
                            case 5 -> System.out.println("Saindo...");
                            default -> System.out.println("Opção inválida");
                        }
                    }
                }
                case 4 -> {
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }
}