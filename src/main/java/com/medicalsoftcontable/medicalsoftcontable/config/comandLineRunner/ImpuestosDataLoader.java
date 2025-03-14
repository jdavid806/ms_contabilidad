package com.medicalsoftcontable.medicalsoftcontable.config.comandLineRunner;

import com.medicalsoftcontable.medicalsoftcontable.core.general.models.Impuestos;
import com.medicalsoftcontable.medicalsoftcontable.core.general.repository.ImpuestoRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
public class ImpuestosDataLoader implements CommandLineRunner {

    private final ImpuestoRepository impuestoRepository;

    public ImpuestosDataLoader(ImpuestoRepository impuestosRepository) {
        this.impuestoRepository = impuestosRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (impuestoRepository.count() == 0) { // Evita duplicados
            List<Impuestos> impuestos = new ArrayList<>();
            impuestos.add(new Impuestos("IVA", "Impuesto sobre el Valor Agregado", "CO", 19.0));
            impuestos.add(new Impuestos("ReteFuente", "Retención en la fuente", "CO", 2.5));
            impuestos.add(new Impuestos("ICA", "Impuesto de Industria y Comercio", "CO", 3.0));
            impuestoRepository.saveAll(impuestos);
        }
    }
}
