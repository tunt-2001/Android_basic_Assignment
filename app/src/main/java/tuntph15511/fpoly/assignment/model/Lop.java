package tuntph15511.fpoly.assignment.model;

public class Lop {
    private int idLop;
    private String maLop;
    private String tenLop;

    public Lop() {
    }

    public Lop(int idLop, String maLop, String tenLop) {
        this.idLop = idLop;
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
