import java.util.ArrayList;
import java.util.List;

public class Album {
    private String nome;
    private List<String> listMusicAlbum = new ArrayList<>();

    public Album (String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getListMusicAlbum() {
        return listMusicAlbum;
    }

    public void addMusicAlbum(String musica){
        this.listMusicAlbum.add(musica);
    }
}