package org.hikinonymous.back.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.CodeMasterEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeMasterDelYnDto {

    private String codeMaster;

    private String delYn;

}
