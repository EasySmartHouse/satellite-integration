package net.easysmarthouse.satellite.integration.transformer;

import net.easysmarthouse.satellite.integration.domain.MemoryRecord;
import net.easysmarthouse.satellite.integration.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

@Service
public class WriteValueTransformer implements ResultTransformer<MemoryRecord, Boolean> {

    @Autowired
    private MemoryService memoryService;

    @Override
    @Transformer
    public Boolean transform(MemoryRecord memoryRecord) {
        return memoryService.write(memoryRecord.getAddress(), memoryRecord.getData());
    }
}
