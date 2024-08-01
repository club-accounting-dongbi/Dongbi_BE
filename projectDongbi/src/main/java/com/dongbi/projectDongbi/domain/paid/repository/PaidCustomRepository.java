package com.dongbi.projectDongbi.domain.paid.repository;

import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.paid.dto.PaidRequest;

import java.util.List;

public interface PaidCustomRepository {

    List<Paid> findByPaidInColNameAndClubMemberId(Long clubMemberId, Long generationNum ,List<PaidRequest> paidList);
}
