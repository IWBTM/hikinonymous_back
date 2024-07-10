package org.hikinonymous.back.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.CodeEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeDelYnDto {

    private Long codeSeq;

    private String delYn;

}
