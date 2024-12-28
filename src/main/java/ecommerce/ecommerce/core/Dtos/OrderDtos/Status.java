package ecommerce.ecommerce.core.Dtos.OrderDtos;

public enum Status {
    PENDING,     // Sipariş oluşturuldu, işlem bekleniyor
    COMPLETED,   // Sipariş tamamlandı
    CANCELLED    // Sipariş iptal edildi
}
