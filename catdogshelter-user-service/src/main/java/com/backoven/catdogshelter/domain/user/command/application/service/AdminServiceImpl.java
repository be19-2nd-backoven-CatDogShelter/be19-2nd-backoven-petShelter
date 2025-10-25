package com.backoven.catdogshelter.domain.user.command.application.service;


import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePostCommentReport;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePostReport;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.comment.*;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.commentreport.*;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.post.*;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.postreport.*;

import com.backoven.catdogshelter.domain.user.command.domain.repository.report.comment.*;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.commentreport.*;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.post.*;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.postreport.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    //    Post
    private final AdoptionPostRepository adoptionPostRepository;
    private final MissingPostRepository missingPostRepository;
    private final PostRepository postRepository;
    private final SightingPostRepository sightingPostRepository;
    private final VolunteerPostRepository volunteerPostRepository;

    //    PostReport
    private final AdoptionPostReportRepository adoptionPostReportRepository;
    private final MissingPostReportRepository missingPostReportRepository;
    private final PostReportRepository postReportRepository;
    private final SightingPostReportRepository sightingPostReportRepository;
    private final VolunteerPostReportRepository volunteerPostReportRepository;

    //    Comment
    private final AdoptionPostCommentRepository adoptionPostCommentRepository;
    private final DonationPostCommentRepository donationPostCommentRepository;
    private final MissingPostCommentRepository missingPostCommentRepository;
    private final PostCommentRepository postCommentRepository;
    private final SightingPostCommentRepository sightingPostCommentRepository;
    private final VolunteerPostCommentRepository volunteerPostCommentRepository;

    //    CommentReport
    private final AdoptionPostCommentReportRepository adoptionPostCommentReportRepository;
    private final DonationPostCommentReportRepository donationPostCommentReportRepository;
    private final MissingPostCommentReportRepository missingPostCommentReportRepository;
    private final PostCommentReportRepository postCommentReportRepository;
    private final SightingPostCommentReportRepository sightingPostCommentReportRepository;
    private final VolunteerPostCommentReportRepository volunteerPostCommentReportRepository;

    @Autowired
    public AdminServiceImpl(AdoptionPostRepository adoptionPostRepository,
                            MissingPostRepository missingPostRepository,
                            PostRepository postRepository,
                            SightingPostRepository sightingPostRepository,
                            VolunteerPostRepository volunteerPostRepository,
                            AdoptionPostReportRepository adoptionPostReportRepository,
                            MissingPostReportRepository missingPostReportRepository,
                            PostReportRepository postReportRepository,
                            SightingPostReportRepository sightingPostReportRepository,
                            VolunteerPostReportRepository volunteerPostReportRepository,
                            AdoptionPostCommentRepository adoptionPostCommentRepository,
                            DonationPostCommentRepository donationPostCommentRepository,
                            MissingPostCommentRepository missingPostCommentRepository,
                            PostCommentRepository postCommentRepository,
                            SightingPostCommentRepository sightingPostCommentRepository,
                            VolunteerPostCommentRepository volunteerPostCommentRepository,
                            AdoptionPostCommentReportRepository adoptionPostCommentReportRepository,
                            DonationPostCommentReportRepository donationPostCommentReportRepository,
                            MissingPostCommentReportRepository missingPostCommentReportRepository,
                            PostCommentReportRepository postCommentReportRepository,
                            SightingPostCommentReportRepository sightingPostCommentReportRepository,
                            VolunteerPostCommentReportRepository volunteerPostCommentReportRepository) {
        this.adoptionPostRepository = adoptionPostRepository;
        this.missingPostRepository = missingPostRepository;
        this.postRepository = postRepository;
        this.sightingPostRepository = sightingPostRepository;
        this.volunteerPostRepository = volunteerPostRepository;
        this.adoptionPostReportRepository = adoptionPostReportRepository;
        this.missingPostReportRepository = missingPostReportRepository;
        this.postReportRepository = postReportRepository;
        this.sightingPostReportRepository = sightingPostReportRepository;
        this.volunteerPostReportRepository = volunteerPostReportRepository;
        this.adoptionPostCommentRepository = adoptionPostCommentRepository;
        this.donationPostCommentRepository = donationPostCommentRepository;
        this.missingPostCommentRepository = missingPostCommentRepository;
        this.postCommentRepository = postCommentRepository;
        this.sightingPostCommentRepository = sightingPostCommentRepository;
        this.volunteerPostCommentRepository = volunteerPostCommentRepository;
        this.adoptionPostCommentReportRepository = adoptionPostCommentReportRepository;
        this.donationPostCommentReportRepository = donationPostCommentReportRepository;
        this.missingPostCommentReportRepository = missingPostCommentReportRepository;
        this.postCommentReportRepository = postCommentReportRepository;
        this.sightingPostCommentReportRepository = sightingPostCommentReportRepository;
        this.volunteerPostCommentReportRepository = volunteerPostCommentReportRepository;
    }


    @Override
    @Transactional
    public void PostReport(String category, int postId, boolean blind) {
        switch (category.toLowerCase()) {
            case "adoption" -> {
                // 신고 처리
                List<AdoptionPostReport> prList = adoptionPostReportRepository.findByPostId(postId);
                for (BasePostReport bpr : prList) {
                    bpr.setStatus(true);
                }
                adoptionPostReportRepository.saveAll(prList);

                //블라인드 처리
                AdoptionPost p = adoptionPostRepository.findById(postId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    adoptionPostRepository.save(p);
                }
            }
            case "missing" -> {
                // 신고 처리
                List<MissingPostReport> prList = missingPostReportRepository.findByPostId(postId);
                for (BasePostReport bpr : prList) {
                    bpr.setStatus(true);
                }
                missingPostReportRepository.saveAll(prList);

                //블라인드 처리
                MissingPost p = missingPostRepository.findById(postId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    missingPostRepository.save(p);
                }
            }
            case "post" -> {
                List<PostReport> prList = postReportRepository.findByPostId(postId);
                for (BasePostReport bpr : prList) {
                    bpr.setStatus(true);
                }
                postReportRepository.saveAll(prList);

                //블라인드 처리
                Post p = postRepository.findById(postId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    postRepository.save(p);
                }
            }
            case "sighting" -> {
                List<SightingPostReport> prList = sightingPostReportRepository.findByPostId(postId);
                for (BasePostReport bpr : prList) {
                    bpr.setStatus(true);
                }
                sightingPostReportRepository.saveAll(prList);

                //블라인드 처리
                SightingPost p = sightingPostRepository.findById(postId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    sightingPostRepository.save(p);
                }
            }
            case "volunteer" -> {
                List<VolunteerPostReport> prList = volunteerPostReportRepository.findByPostId(postId);
                for (BasePostReport bpr : prList) {
                    bpr.setStatus(true);
                }
                volunteerPostReportRepository.saveAll(prList);

                //블라인드 처리
                VolunteerPost p = volunteerPostRepository.findById(postId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    volunteerPostRepository.save(p);
                }
            }
        }
        ;
    }

    @Override
    @Transactional
    public void CommentReport(String category, int commentId, boolean blind) {
        switch (category.toLowerCase()) {
            case "adoption" -> {
                // 신고 처리
                List<AdoptionPostCommentReport> prcList = adoptionPostCommentReportRepository.findByCommentId(commentId);
                for (BasePostCommentReport bpcr : prcList) {
                    bpcr.setStatus(true);
                }
                adoptionPostCommentReportRepository.saveAll(prcList);

                //블라인드 처리
                AdoptionPostComment p = adoptionPostCommentRepository.findById(commentId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    adoptionPostCommentRepository.save(p);
                }
            }
            case "donation" -> {
                // 신고 처리
                List<DonationPostCommentReport> prcList = donationPostCommentReportRepository.findByCommentId(commentId);
                for (BasePostCommentReport bpcr : prcList) {
                    bpcr.setStatus(true);
                }
                donationPostCommentReportRepository.saveAll(prcList);

                //블라인드 처리
                DonationPostComment p = donationPostCommentRepository.findById(commentId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    donationPostCommentRepository.save(p);
                }
            }
            case "missing" -> {
                // 신고 처리
                List<MissingPostCommentReport> prcList = missingPostCommentReportRepository.findByCommentId(commentId);
                for (BasePostCommentReport bpcr : prcList) {
                    bpcr.setStatus(true);
                }
                missingPostCommentReportRepository.saveAll(prcList);

                //블라인드 처리
                MissingPostComment p = missingPostCommentRepository.findById(commentId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    missingPostCommentRepository.save(p);
                }
            }
            case "post" -> {
                List<PostCommentReport> prcList = postCommentReportRepository.findByCommentId(commentId);
                for (BasePostCommentReport bpcr : prcList) {
                    bpcr.setStatus(true);
                }
                postCommentReportRepository.saveAll(prcList);

                //블라인드 처리
                PostComment p = postCommentRepository.findById(commentId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    postCommentRepository.save(p);
                }
            }
            case "sighting" -> {
                List<SightingPostCommentReport> prcList = sightingPostCommentReportRepository.findByCommentId(commentId);
                for (BasePostCommentReport bpcr : prcList) {
                    bpcr.setStatus(true);
                }
                sightingPostCommentReportRepository.saveAll(prcList);

                //블라인드 처리
                SightingPostComment p = sightingPostCommentRepository.findById(commentId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    sightingPostCommentRepository.save(p);
                }
            }
            case "volunteer" -> {
                List<VolunteerPostCommentReport> prcList = volunteerPostCommentReportRepository.findByCommentId(commentId);
                for (BasePostCommentReport bpcr : prcList) {
                    bpcr.setStatus(true);
                }
                volunteerPostCommentReportRepository.saveAll(prcList);

                //블라인드 처리
                VolunteerPostComment p = volunteerPostCommentRepository.findById(commentId).orElse(null);
                if (p != null) {
                    p.setBlinded(blind);
                    volunteerPostCommentRepository.save(p);
                }
            }
        }
    }
}
