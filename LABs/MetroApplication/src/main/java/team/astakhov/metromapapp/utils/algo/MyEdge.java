package team.spirit.metromapapp.utils.algo;

public class MyEdge {
    public MyEdge(Integer time, Integer id1, Integer id2, String name1, String name2, String vetka1, String vetka2) {
        this.time = time;
        this.id1 = id1;
        this.id2 = id2;
        this.name1 = name1;
        this.name2 = name2;
        this.vetka1 = vetka1;
        this.vetka2 = vetka2;
    }

    private Integer time;
    private Integer id1;
    private Integer id2;
    private String name1;
    private String name2;
    private String vetka1;
    private String vetka2;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getVetka1() {
        return vetka1;
    }

    public void setVetka1(String vetka1) {
        this.vetka1 = vetka1;
    }

    public String getVetka2() {
        return vetka2;
    }

    public void setVetka2(String vetka2) {
        this.vetka2 = vetka2;
    }
}
