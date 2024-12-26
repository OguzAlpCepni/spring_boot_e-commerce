package ecommerce.ecommerce.model;

public enum OrderStatus {
    PENDING,     // Sipariş oluşturuldu, işlem bekleniyor
    COMPLETED,   // Sipariş tamamlandı
    CANCELLED    // Sipariş iptal edildi
}