package ci.orange.digital.center.ahazzran.webservice.services;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.ApprenantOuputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CoursStatutDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ProfilDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ProgressionHebdoDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Chapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.Cours;
import ci.orange.digital.center.ahazzran.webservice.entities.CoursDetail;
import ci.orange.digital.center.ahazzran.webservice.entities.ExerciceChapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.Progression;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionChapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionCours;
import ci.orange.digital.center.ahazzran.webservice.repositories.ChapitreRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.CoursDetailRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.CoursRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ExerciceChapitreRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ProgressionChapitreRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ProgressionCoursRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ProgressionRepository;


@Service
public class ProgressionService implements IProgressionService{

    private ProgressionRepository progressionRepository;
    private ProgressionCoursRepository progressionCoursRepository;
    private ProgressionChapitreRepository progressionChapitreRepository;
    private CoursDetailRepository coursDetailRepository;
    private ExerciceChapitreRepository exerciceChapitreRepository;
    private ChapitreRepository chapitreRepository;
    private CoursRepository coursRepository;




    public ProgressionService(final ProgressionCoursRepository progressionCoursRepository,final ChapitreRepository chapitreRepository,
    final ProgressionChapitreRepository progressionChapitreRepository, final CoursRepository coursRepository,
    final CoursDetailRepository coursDetailRepository,
    final ExerciceChapitreRepository exerciceChapitreRepository, final ProgressionRepository progressionRepository){
        this.progressionCoursRepository = progressionCoursRepository;
        this.progressionChapitreRepository = progressionChapitreRepository;
        this.coursDetailRepository = coursDetailRepository;
        this.exerciceChapitreRepository = exerciceChapitreRepository;
        this.progressionRepository = progressionRepository;
        this.chapitreRepository = chapitreRepository;
        this.coursRepository = coursRepository;
    }

   

    @Override
    public List<ApprenantOuputDto> getTop30ApprenantsTriesParXpParLangue(int langueId) {

        List<Progression> progressionsTriees = progressionRepository.findAllByLangueLangueIdOrderByXpAsc(langueId);
        return progressionsTriees.stream()
                .limit(30)
                .map(progression -> new ApprenantOuputDto(
                        progression.getApprenant().getEmail(),
                        progressionsTriees.indexOf(progression) + 1,
                        progression.getXp())).collect(Collectors.toList());

    }

    @Override
    public int getRangApprenantParLangue(int apprenantId, int langueId) {

        List<Progression> progressionsTriees = progressionRepository.findAllByLangueLangueIdOrderByXpAsc(langueId);
        
        for (int i = 0; i < progressionsTriees.size(); i++) {
            if (progressionsTriees.get(i).getApprenant().getApprenantId() == apprenantId) {
                return i + 1; // Le rang est l'index + 1
            }
        }
        return -1;
    }


    @Override
    public int calculerXpTotal() {
        
        List<ProgressionCours> coursTermines = progressionCoursRepository.findByEstTermine(true);

        List<CoursDetail> coursDetails = coursDetailRepository.findByCours_ProgressionCoursIn(coursTermines);

        int sommeRecompensesCours = coursDetails.stream()
                .mapToInt(detail -> detail.getRecompense()).sum();

        List<ProgressionChapitre> chapitresTermines = progressionChapitreRepository.findByEstTermine(true);

        List<ExerciceChapitre> exercicesChapitres = exerciceChapitreRepository.findByChapitre_ProgressionChapitreIn(chapitresTermines);

        int sommeRecompensesChapitres = exercicesChapitres.stream().mapToInt(ExerciceChapitre::getRecompense).sum();

        return sommeRecompensesCours + sommeRecompensesChapitres;
    }
    
    @Override
    public List<ApprenantOuputDto> getApprenantsTriesParXpParLangue(int langueId) {

        List<Progression> progressionsTriees = progressionRepository.findAllByLangueLangueIdOrderByXpAsc(langueId);

       return progressionsTriees.stream()
                .map(progression -> new ApprenantOuputDto(
                        progression.getApprenant().getEmail(),
                        progressionsTriees.indexOf(progression) + 1,
                        progression.getXp())).collect(Collectors.toList());
    }


    // Comment inserer les valeurs Xp dans la table.
    public void initializeProgression(int apprenantId, int langueId) {
        // Fetch the Progression entity based on apprenant, langue, and niveau
        Progression progression = progressionRepository.findByApprenantApprenantIdAndLangueLangueId(apprenantId, langueId);
        
        // Fetch all chapters and courses
        List<Chapitre> chapitres = chapitreRepository.findAll();
        List<Cours> cours = coursRepository.findAll();

        // Iterate through chapters and courses to create ProgressionChapitre and ProgressionCours
        for (Chapitre chapitre : chapitres) {

            ProgressionChapitre existingProgressionChapitre = progressionChapitreRepository
            .findByProgressionAndChapitre(progression, chapitre);

            if (existingProgressionChapitre == null) {
            ProgressionChapitre progressionChapitre = new ProgressionChapitre();
            progressionChapitre.setEstTermine(false);
            progressionChapitre.setProgression(progression);
            progressionChapitre.setChapitre(chapitre);
            progressionChapitreRepository.save(progressionChapitre);

            for (Cours coursEntity : cours) {
                if (coursEntity.getChapitre().getChapitreId() == chapitre.getChapitreId()) {

                    ProgressionCours existingProgressionCours = progressionCoursRepository
                        .findByProgressionChapitreAndCours(progressionChapitre, coursEntity);

                    if (existingProgressionCours == null) {

                    ProgressionCours progressionCours = new ProgressionCours();
                    progressionCours.setEstTermine(false);
                    progressionCours.setProgressionChapitre(progressionChapitre);
                    progressionCours.setCours(coursEntity);
                    progressionCoursRepository.save(progressionCours);

                    }
                }
            }

        }
        }

    }


    // A appeler par le front
    public void updateFinCours(int progressionCoursId, Timestamp finCours) {
        ProgressionCours progressionCours = progressionCoursRepository.findById(progressionCoursId).orElseThrow();
        progressionCours.setFinCours(finCours);
        progressionCours.setEstTermine(true);
        progressionCoursRepository.save(progressionCours);
    }


    // A appeler par le front
    public void updateDebutChapitre(int progressionChapitreId, Timestamp debutCours) {
        ProgressionChapitre progressionChapitre = progressionChapitreRepository.findById(progressionChapitreId).orElseThrow();
        progressionChapitre.setDebutChapitre(debutCours);
        progressionChapitre.setEstTermine(false);
        progressionChapitreRepository.save(progressionChapitre);
    }


    public void verifierEtMettreAJourStatutChapitre(ProgressionChapitre progressionChapitre) {
        boolean tousCoursTermines = progressionChapitre.getProgressionCours().stream().allMatch(ProgressionCours::isEstTermine);

        if (tousCoursTermines && !progressionChapitre.isEstTermine()) {
        progressionChapitre.setEstTermine(true);
        progressionChapitre.setFinChapitre(new Timestamp(System.currentTimeMillis()));
        }

        progressionChapitreRepository.save(progressionChapitre);

    }


    @Override
    public ProfilDto getWeeklyProgression(int apprenantId, int langueId) {

        int nbreDeJoursActivite = progressionRepository.countDistinctDaysByApprenantApprenantId(apprenantId);

        initializeProgression(apprenantId,langueId);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime debutSemaine = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDateTime finSemaine = now.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
        Timestamp debut = Timestamp.valueOf(debutSemaine);
        Timestamp fin = Timestamp.valueOf(finSemaine);
        

        Progression progression = progressionRepository.findByApprenantApprenantIdAndLangueLangueId(apprenantId, langueId);
        int progressionId = progression.getProgressionId();
        

        List<ProgressionChapitre> chapitres = progressionChapitreRepository.findByProgressionProgressionId(progressionId);
        List<ProgressionHebdoDto> progressionHebdoList = new ArrayList<>();

        Map<String, Integer> recompenseOutput = new HashMap<>();

        for (ProgressionChapitre chapitre : chapitres) {
            
            List<CoursStatutDto> coursStatutDtos = getCoursTerminesPourSemaine(chapitre,debut,fin);

            int sum = 0;
            for (int i=0;i < coursStatutDtos.size(); i++) {

                LocalDate date = coursStatutDtos.get(i).getFinalDate().toLocalDate();
                String jour = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH);

                if (i>= 2 && coursStatutDtos.get(i).isEstTermine() && coursStatutDtos.get(i).getFinalDate() == coursStatutDtos.get(i-1).getFinalDate()) {
                    sum = coursStatutDtos.get(i).getPoint() + sum;
    
                    recompenseOutput.put(jour,sum); }
    
                if (coursStatutDtos.get(i).isEstTermine()) {
                    recompenseOutput.put(jour,coursStatutDtos.get(i).getPoint());
                }
           
            }

            ProgressionHebdoDto progressionHebdoDto = new ProgressionHebdoDto(chapitre,coursStatutDtos);
            progressionHebdoList.add(progressionHebdoDto);
            verifierEtMettreAJourStatutChapitre(chapitre);
            
        }

        return new ProfilDto(progression,nbreDeJoursActivite,recompenseOutput);

    }


    public List<CoursStatutDto> getCoursTerminesPourSemaine(ProgressionChapitre progressionChapitre,Timestamp debutSemaine, Timestamp finSemaine) {
        List<ProgressionCours> progressionCours = progressionCoursRepository.findByProgressionChapitreAndFinCoursBetweenAndEstTermineTrue(progressionChapitre,debutSemaine, finSemaine);

        return progressionCours.stream().map(pc -> new CoursStatutDto(pc)).collect(Collectors.toList());
    }

    public List<ProgressionChapitre> getChapitresTerminesPourSemaine(int id, Timestamp debutSemaine, Timestamp finSemaine) {
        return progressionChapitreRepository.findByProgressionProgressionIdAndFinChapitreBetweenAndEstTermineTrue(id, debutSemaine, finSemaine);
    }


}

/* Remplir automatiquement les tables progressionCours et progressionChapitres grace a la liste des Ids des cours et Chapitres, de base les booleans sont a false partout.

les valeurs de l'attribut progressions_id de progressionschapitre proviendront de la table progression pour un seule ligne de cette table. 
Elles seront répétées autant de fois qu'il y'aura une valeur d'atrribut chapitre id differente.

Les valeurs de progressionschapitre_id de progressionscours seront repetees autant de fois qu'il n'y a de relations entre un chapitre et ses cours.
Et cela pour chaque chapitre.

Se baser sur les relations entre un id chapitre et ses id cours qui lui sont associées(A revoir) pour verifier que tout les cours d'un chapitre sont terminés.

    

 * 
 * Au fait on doit etre capable de connaitre le nombre de chapitres terminés au cours d'une semaine.
 * 
 * Un chapitre est terminé si tous les cours qui lui sont associés sont terminés (autrement dis passage a true).
 * Les booleans passent a true si la valeur de finchapitre ou fincours sont modifes.
 * 
 * Pour verfier si un chapitre est terminé, on verifie si tous les cours qui lui sont associés sont a true.
 * 
 * Si un seul, est a false alors le boolean du chapitre reste egalement a false, sinon il passe a True.
 * 
 * Maintenant le souci est de savoir sur quel periode on est ?
 * 
 * Vu que nous voulons pour une semaine.
 * 
 * A la fin, il faudra retourner la liste des cours terminés et non terminés associés a un chapitre (Precisez egalement si ce 
 * ce chapitre est terminé) au cours de la semaine. Si plusieurs chapitres ont été abordés au cours de cette semaine,
 *  il faudra retourner effectivement, la liste de ces chapitres avec leurs etats et celles de leurs cours.
 * 
 * 
 * 
 * Cache cache = cacheManager.getCache("recompenseCache");
        if (cache != null) {
            Integer recompense = cache.get("recompenseValue", Integer.class);
            if (recompense != null) {
                // Utilisez la valeur de recompense ici
            }
        }
 * 
 * 
 */

 