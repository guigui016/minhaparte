import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nome;
    private List<Musica> musicPlaylist = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicPlaylist() {
        return musicPlaylist;
    }

    public void setMusicPlaylist(List<Musica> musicPlaylist) {
        this.musicPlaylist = musicPlaylist;
    }

    // Método para adicionar músicas
    public void addMusicPlaylist(Musica musica) {
        this.musicPlaylist.add(musica);
    }

    // Método para remover músicas
    public void removeMusicPlaylist(Musica musica) {
        this.musicPlaylist.remove(musica);
    }

    // Método para mostrar as músicas
    public void mostrarMusicas() {
        if (musicPlaylist.isEmpty()) {
            System.out.println("A playlist está vazia.");
        } else {
            System.out.println("Músicas na playlist '" + nome + "':");
            for (Musica musica : musicPlaylist) {
                System.out.println(musica.getTitulo() + " - " + musica.getArtista());
            }
        }
    }

    // Mostrar as músicas com o número
    public void mostrarMusicasID() {
        int i = 1;
        if (musicPlaylist.isEmpty()) {
            System.out.println("A playlist está vazia.");
        } else {
            System.out.println("Músicas na playlist '" + nome + "':");
            for (Musica musica : musicPlaylist) {
                System.out.println(i + ": " +musica.getTitulo() + " - " + musica.getArtista());
                i += 1;
            }
        }
    }

    // Método para editar a posição da música
    public void editarPosicaoMusica(int posicaoAtual, int novaPosicao) {
        if (posicaoAtual >= 0 && posicaoAtual < musicPlaylist.size() &&
            novaPosicao >= 0 && novaPosicao < musicPlaylist.size()) {
            Musica musica = musicPlaylist.remove(posicaoAtual);
            musicPlaylist.add(novaPosicao, musica);
        } else {
            System.out.println("Posições inválidas.");
        }
    }

    // Método para excluir a playlist
    public void excluirPlaylist() {
        this.musicPlaylist.clear();
    }
    
    public void reproduzirPlaylist() {
        for (Musica musica : musicPlaylist) {
            System.out.println("Reproduzindo: " + musica.getTitulo());
            musica.reproduzir();
        }
    }    
    
}