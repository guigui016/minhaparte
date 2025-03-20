import java.util.ArrayList;
import java.util.List;

public class Artista {
    private String nome;
    private List<String> listMusicArtista = new ArrayList<>();

    public Artista(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getListMusicArtista() {
        return listMusicArtista;
    }

    public void addMusicArtista(String musica) {
        this.listMusicArtista.add(musica);
    }
}