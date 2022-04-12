package com.sistemonlinevoting.Server.dataBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CandidateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/candidate")
    public Candidate saveCandidate(@RequestBody Candidate newCandidate) {
        return candidateService.newCandidate(newCandidate);
    }

    @GetMapping("/candidate/president")
    public List<CandidateModel> getCanditatesPresident(){
      return candidateService.getAllPresident();
    }

    @GetMapping("/candidate/parliamentary")
    public List<CandidateModel> getCanditatesParliamentary(){
      return candidateService.getAllParliamentary();
    }

    @GetMapping("/candidate/europarliamentary")
    public List<CandidateModel> getCanditatesEuroparliamentary(){
       return candidateService.getAllEuroParlimentary();
    }

   @GetMapping("candidate/{id}")
   public CandidateModel getCandidateByIdUser(@PathVariable String id){
       return candidateService.getCandidateByIdUser(id);
   }

   @PostMapping("candidate/vote/{id}")
   public CandidateModel voteCandidate(@PathVariable(value = "id") String idUser){
        return candidateService.voteCandidate(idUser);
   }
}
