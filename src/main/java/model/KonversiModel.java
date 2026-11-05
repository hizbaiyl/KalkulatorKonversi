package model;


public class KonversiModel {
   // Faktor konversi relatif terhadap 1 Meter
    private static final double METER_KE_METER = 1.0;
    private static final double FEET_KE_METER  = 0.3048;
    private static final double YARD_KE_METER  = 0.9144;
    private static final double MILES_KE_METER = 1609.344;
    public double konversi(double nilai, String satuanAsal, String satuanTujuan) {
        // 1. Ubah nilai asal menjadi meter
        double nilaiDalamMeter = nilai * getFaktorKeMeter(satuanAsal);
        // 2. Ubah nilai dalam meter ke satuan tujuan
        return nilaiDalamMeter / getFaktorKeMeter(satuanTujuan);
    }
    private double getFaktorKeMeter(String satuan) {
        switch (satuan.toLowerCase()) {
            case "meter":
                return METER_KE_METER;
            case "feet":
                return FEET_KE_METER;
            case "yard":
                return YARD_KE_METER;
            case "miles":
                return MILES_KE_METER;
            default:
                throw new IllegalArgumentException("Satuan tidak valid: " + satuan);
        }
    }   
}
