package JavaHack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Jugador extends Personaje{
    private String nombre;
    private int xp;
    private List<Item> inventario;
    private Map<String,Equipamiento> equipamiento;
    private Arma arma;
    
    public Jugador(String nombre){
        super();
        this.nombre = nombre;
        this.xp = 0;
        this.inventario = new ArrayList<Item>();
        this.equipamiento = new HashMap<String, Equipamiento>();
        this.arma = new Arma("Espada basica",0.5f , 0.25f, getRepresentacion());   //espada basica
        inventario.add(arma);

        //equipamiento de prueba 
        Equipamiento equiArmDefecto = new Equipamiento('A', "Armadura de cuero", "Armadura", 2, 1);
        Equipamiento equiBotaDefecto = new Equipamiento('B', "Botas basicas", "Botas", 1, 1);
        
        inventario.add(equiArmDefecto);
        inventario.add(equiBotaDefecto);
        equipamiento.put(equiArmDefecto.getTipo(), equiArmDefecto);
        equipamiento.put(equiBotaDefecto.getTipo(), equiBotaDefecto);
    }

    @Override
    public char getRepresentacion(){
        return 'J';
    }

    public float calcularAtaque(){

        float atq = arma.calcularAtaqueArma(null, null);

        for (Equipamiento eq : equipamiento.values()) {
            atq += eq.getIntel() + eq.getStr();
        }

        return 3 * getNivel() + atq;
    }


    public void ganarXp(int xp){
        this.xp += xp;
        if (this.xp >= 100) {
            this.xp -= 100; 
            this.setNivel(this.getNivel() + 1);
            System.out.println(this.getnombre() + " ha subido de nivel a " + this.getNivel() + "!");
        }
    }

    public void equipar(Arma arma){
        this.arma = arma;
    }

    public void equipar(Equipamiento equipamiento){
        if (equipamiento != null) {
            String tipoEquipamiento = equipamiento.getTipo();
            if (this.equipamiento.containsKey(tipoEquipamiento)) {
                Equipamiento equipAnterior = this.equipamiento.get(tipoEquipamiento);
                this.inventario.add(equipAnterior);
            }

            this.equipamiento.put(tipoEquipamiento, equipamiento);
            this.inventario.remove(equipamiento);


        }
    }

    public void verEstadisticas(){
        System.out.println("Estadisticas: ");
        System.out.println("Nombre : " + getnombre());
        System.out.println("Hp : " + getHp());
        System.out.println("Xp : " + getXp());
        System.out.println("Arma : " + arma.getNombre()  + "\n multiplicador de fuerza : " + arma.getMul_str() + "\n multiplicador de inteligencia : " + arma.getMul_int());
    }

    public void verInventario() {
        System.out.println("Inventario:");
        for (Item item : inventario) {
            if (item instanceof Arma) {
                System.out.println("- Arma: " + item.getNombre() + " (Fuerza: "+ arma.getMul_str() + " Inteligencia: "+ arma.getMul_int() + ")");
            } else if (item instanceof Equipamiento) {
                Equipamiento equipamiento = (Equipamiento) item;
                System.out.println("- Equipamiento: " + equipamiento.getNombre() + " (Tipo: " + equipamiento.getTipo() + ", Str: " + equipamiento.getStr() + ", Int: " + equipamiento.getIntel() + ")");
            }   
            
        }
    }


    public int getXp() {
        return xp;
    }

    public String getnombre(){
        return nombre;
    }

    public Arma getArma(){
        return arma;
    }
    
    public Map<String, Equipamiento> getEquipamiento() {
        return equipamiento;
    }

    public List<Item> getInventario() {
        return inventario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setInventario(List<Item> inventario) {
        this.inventario = inventario;
    }

    public void setEquipamiento(Map<String, Equipamiento> equipamiento) {
        this.equipamiento = equipamiento;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    
}