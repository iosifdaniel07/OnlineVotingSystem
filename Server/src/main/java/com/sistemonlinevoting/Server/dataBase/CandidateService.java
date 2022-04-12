package com.sistemonlinevoting.Server.dataBase;

import com.sistemonlinevoting.Server.EncryptionDecryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CandidateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateService.class);

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private UserRepository userRepository;

    @Async
    public Candidate newCandidate(Candidate candidate){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

             /*  User user = userRepository.findByid(Long.parseLong(candidate.getIdUser()));
               byte[] decodedKey = EncryptionDecryption.decrypWithPrivateKey(user.getSessionKey());
                SecretKey secretKey = new SecretKeySpec(decodedKey, 0,decodedKey.length, "DES");

                 candidate.setName(EncryptionDecryption.decryptWithSessionKey(candidate.getName(),secretKey));
                 candidate.setDescription(EncryptionDecryption.decryptWithSessionKey(candidate.getDescription(),secretKey));
                 candidate.setIdUser(EncryptionDecryption.decryptWithSessionKey(candidate.getIdUser(),secretKey));
                 candidate.setRol(EncryptionDecryption.decryptWithSessionKey(candidate.getRol(),secretKey));*/

                 candidateRepository.save(candidate);
                 LOGGER.error("salvat");
            }
        });

        thread.start();

        try{
            thread.join();
        }catch(InterruptedException e){
            LOGGER.error("create problem");
        }

        return candidate;
    }

    @Async
    public List<CandidateModel> getAllPresident(){

        List<Candidate> list = candidateRepository.findAll();
        List<CandidateModel> listPresident = new  ArrayList<>();

        for (Candidate candidat:list) {
            if(candidat.getRol().equals("Presedinte")){

                CandidateModel c = new CandidateModel();
                c.setName(candidat.getName());
                c.setIdUser(candidat.getIdUser());
                c.setDescription(candidat.getDescription());
                c.setRol(candidat.getRol());
                c.setImage(Base64.getEncoder().encodeToString(candidat.getImage()));
                listPresident.add(c);
            }
        }

        return listPresident;
    }

    @Async
    public List<CandidateModel> getAllParliamentary(){

        List<Candidate> list = candidateRepository.findAll();
        List<CandidateModel> listParlamentary = new  ArrayList<>();

        for (Candidate candidat:list) {
            if(candidat.getRol().equals("Parlamentar")){

                CandidateModel c = new CandidateModel();
                c.setName(candidat.getName());
                c.setIdUser(candidat.getIdUser());
                c.setDescription(candidat.getDescription());
                c.setRol(candidat.getRol());
                c.setImage(Base64.getEncoder().encodeToString(candidat.getImage()));
                listParlamentary.add(c);
            }
        }

        return listParlamentary;

    }

    @Async
    public List<CandidateModel> getAllEuroParlimentary(){

        List<Candidate> list = candidateRepository.findAll();
        List<CandidateModel> listParlamentary = new  ArrayList<>();

        for (Candidate candidat:list) {
            if(candidat.getRol().equals("EuroParlamentar")){

                CandidateModel c = new CandidateModel();
                c.setName(candidat.getName());
                c.setIdUser(candidat.getIdUser());
                c.setDescription(candidat.getDescription());
                c.setRol(candidat.getRol());
                c.setImage(Base64.getEncoder().encodeToString(candidat.getImage()));
                listParlamentary.add(c);
            }
        }

        return listParlamentary;
    }

    @Async
    public CandidateModel getCandidateByIdUser(String idUser){

        CandidateModel candidateModel = new CandidateModel();
        Candidate candidate = candidateRepository.findByIdUser(idUser);

        candidateModel.setName(candidate.getName());
        candidateModel.setImage(Base64.getEncoder().encodeToString(candidate.getImage()));
        candidateModel.setIdUser(candidate.getIdUser());
        candidateModel.setRol(candidate.getRol());
        candidateModel.setDescription(candidate.getDescription());

        return candidateModel;

    }

    @Async
    public CandidateModel voteCandidate(String idUser){

         CandidateModel candidateModel = new CandidateModel();
         Candidate candidate = candidateRepository.findByIdUser(idUser);
         int scor = candidate.getScor() + 1;
         candidate.setScor(scor);
        candidateRepository.save(candidate);

        return candidateModel;
    }
}
