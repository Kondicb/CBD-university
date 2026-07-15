package com.fakultet.schedulingservice.service;

import com.fakultet.schedulingservice.client.AcademicServiceClient;
import com.fakultet.schedulingservice.client.UsersServiceClient;
import com.fakultet.schedulingservice.dto.PredmetInfo;
import com.fakultet.schedulingservice.dto.ProfesorInfo;
import com.fakultet.schedulingservice.dto.RasporedStavkaResponse;
import com.fakultet.schedulingservice.exception.BusinessRuleViolationException;
import com.fakultet.schedulingservice.exception.ResourceNotFoundException;
import com.fakultet.schedulingservice.model.IzvodjenjeNastave;
import com.fakultet.schedulingservice.model.Semestar;
import com.fakultet.schedulingservice.model.Termin;
import com.fakultet.schedulingservice.repository.IzvodjenjeNastaveRepository;
import com.fakultet.schedulingservice.repository.SemestarRepository;
import com.fakultet.schedulingservice.repository.TerminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IzvodjenjeNastaveService {

    private final IzvodjenjeNastaveRepository izvodjenjeRepository;
    private final SemestarRepository semestarRepository;
    private final TerminRepository terminRepository;
    private final AcademicServiceClient academicServiceClient;
    private final UsersServiceClient usersServiceClient;

    public IzvodjenjeNastaveService(IzvodjenjeNastaveRepository izvodjenjeRepository,
                                     SemestarRepository semestarRepository,
                                     TerminRepository terminRepository,
                                     AcademicServiceClient academicServiceClient,
                                     UsersServiceClient usersServiceClient) {
        this.izvodjenjeRepository = izvodjenjeRepository;
        this.semestarRepository = semestarRepository;
        this.terminRepository = terminRepository;
        this.academicServiceClient = academicServiceClient;
        this.usersServiceClient = usersServiceClient;
    }

    public IzvodjenjeNastave create(Integer predmetId, Integer semestarId,
                                     Integer profesorId, Integer terminId) {


        PredmetInfo predmet = academicServiceClient.getPredmetById(predmetId);


        ProfesorInfo profesor = usersServiceClient.getProfesorById(profesorId);

        Semestar semestar = semestarRepository.findById(semestarId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Semestar sa id=" + semestarId + " ne postoji"));

        Termin termin = terminRepository.findById(terminId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Termin sa id=" + terminId + " ne postoji"));

        List<IzvodjenjeNastave> profesorovaIzvodjenja = izvodjenjeRepository
                .findById_ProfesorIdAndId_SemestarId(profesorId, semestarId);

        boolean konfliktProfesora = profesorovaIzvodjenja.stream().anyMatch(iz -> {
            Termin postojeci = iz.getTermin();
            return postojeci.getDanUNedelji().equals(termin.getDanUNedelji())
                    && termin.getVremeOd().isBefore(postojeci.getVremeDo())
                    && postojeci.getVremeOd().isBefore(termin.getVremeDo());
        });

        if (konfliktProfesora) {
            throw new BusinessRuleViolationException(String.format(
                    "Profesor '%s' vec ima nastavu u ovom semestru u terminu koji se poklapa (%s %s-%s)",
                    profesor.getPrezime(), termin.getDanUNedelji(), termin.getVremeOd(), termin.getVremeDo()));
        }

        long brojStudenata = usersServiceClient.getBrojUpisanihStudenata(predmetId);
        int kapacitetUcionice = termin.getUcionica().getKapacitet();

        if (brojStudenata > kapacitetUcionice) {
            throw new BusinessRuleViolationException(String.format(
                    "Ucionica '%s' ima kapacitet %d, a na predmet '%s' je upisano %d studenata",
                    termin.getUcionica().getOznaka(), kapacitetUcionice, predmet.getNaziv(), brojStudenata));
        }

        IzvodjenjeNastave izvodjenje = new IzvodjenjeNastave(predmetId, semestar, profesorId, termin);
        return izvodjenjeRepository.save(izvodjenje);
    }

    public List<RasporedStavkaResponse> getRasporedZaSemestar(Integer semestarId) {
        List<IzvodjenjeNastave> izvodjenja = izvodjenjeRepository.findById_SemestarId(semestarId);

        return izvodjenja.stream().map(iz -> {
            PredmetInfo predmet = academicServiceClient.getPredmetById(iz.getId().getPredmetId());
            ProfesorInfo profesor = usersServiceClient.getProfesorById(iz.getId().getProfesorId());
            Termin termin = iz.getTermin();

            return new RasporedStavkaResponse(
                    predmet.getNaziv(),
                    profesor.getPrezime(),
                    termin.getDanUNedelji(),
                    termin.getVremeOd().toString(),
                    termin.getVremeDo().toString(),
                    termin.getUcionica().getOznaka());
        }).collect(Collectors.toList());
    }
}
