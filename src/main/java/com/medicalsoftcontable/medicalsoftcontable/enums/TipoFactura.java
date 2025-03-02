package com.medicalsoftcontable.medicalsoftcontable.enums;

public enum TipoFactura {

    FACTURA_VENTA(1),
    FACTURA_COMPRA(2),
    FACTURA_ENTIDADES(3),
    FACTURA_RECURRENTE(4),
    DOCUMENTO_SOPORTE(5),
    COTIZACION(6),
    ORDEN_DE_COMPRA(7);

    TipoFactura(int tipoFactura){}
}
