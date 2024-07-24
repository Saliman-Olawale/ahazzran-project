package ci.orange.digital.center.ahazzran.webservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailTemplateDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.DefinirCoursInputOuputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.NiveauInfo;
import ci.orange.digital.center.ahazzran.webservice.dtos.TemplateReponseDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Chapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;
import ci.orange.digital.center.ahazzran.webservice.entities.Cours;
import ci.orange.digital.center.ahazzran.webservice.entities.CoursDetail;
import ci.orange.digital.center.ahazzran.webservice.entities.CoursDetailTemplate;
import ci.orange.digital.center.ahazzran.webservice.entities.Evaluation;
import ci.orange.digital.center.ahazzran.webservice.entities.ExerciceChapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;
import ci.orange.digital.center.ahazzran.webservice.entities.Niveau;
import ci.orange.digital.center.ahazzran.webservice.entities.Progression;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionChapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionCours;
import ci.orange.digital.center.ahazzran.webservice.repositories.ChapitreRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ContenuLangueRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.CoursDetailRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.CoursDetailTemplateRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.CoursRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.EvaluationRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ExerciceChapitreRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.NiveauRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ProgressionCoursRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ProgressionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CoursDetailService implements ICoursDetailsService {

    private final ContenuLangueRepository contenuLangueRepository;
    private final StrategyManager strategyManager;
    private final CoursDetailFactoryService coursDetailFactoryService;
    private final CoursDetailTemplateRepository coursDetailTemplateRepository;
    private final CoursDetailRepository coursDetailRepository;
    private final CoursRepository coursRepository;
    private final NiveauRepository niveauRepository;
    private final ProgressionRepository progressionRepository;
    private final ProgressionCoursRepository progressionCoursRepository;
    private final EvaluationRepository evaluationRepository;
    private final ExerciceChapitreRepository exerciceChapitreRepository;
    private final RepeterService repeterService;
    private final ChapitreRepository chapitreRepository;
    private static final Logger logger = LoggerFactory.getLogger(CoursDetailService.class);


    public CoursDetailService(ContenuLangueRepository contenuLangueRepository, StrategyManager strategyManager,ProgressionCoursRepository progressionCoursRepository,
            final CoursRepository coursRepository, EvaluationRepository evaluationRepository,ExerciceChapitreRepository exerciceChapitreRepository,
            RepeterService repeterService, CoursDetailFactoryService coursDetailFactoryService,ProgressionRepository progressionRepository,
            CoursDetailTemplateRepository coursDetailTemplateRepository, CoursDetailRepository coursDetailRepository,
            NiveauRepository niveauRepository, ChapitreRepository chapitreRepository) {
        this.contenuLangueRepository = contenuLangueRepository;
        this.strategyManager = strategyManager;
        this.coursDetailFactoryService = coursDetailFactoryService;
        this.coursDetailTemplateRepository = coursDetailTemplateRepository;
        this.coursDetailRepository = coursDetailRepository;
        this.coursRepository = coursRepository;
        this.repeterService = repeterService;
        this.niveauRepository = niveauRepository;
        this.evaluationRepository = evaluationRepository;
        this.progressionRepository = progressionRepository;
        this.progressionCoursRepository = progressionCoursRepository;
        this.exerciceChapitreRepository = exerciceChapitreRepository;
        this.chapitreRepository = chapitreRepository;
    }


    @Override
    public List<CoursDetailDto> getCoursDetail(int id_cours, int langueId, String type) {

        if (id_cours <= 0) {
            throw new IllegalArgumentException("L'ID du cours doit être supérieur à 0");
        }

        List<ContenuLangue> contenuLangues = contenuLangueRepository.findAllByLangueLangueId(langueId);

        if (contenuLangues.isEmpty()) {
            throw new RuntimeException("Aucun contenu trouvé pour l'ID de la langue : " + langueId);
        }

        List<ICoursDetail> coursDetails = coursDetailFactoryService.getCoursDetailById(id_cours, type);

        List<Integer> contenulangueids = getIdContenuLangues(coursDetails);

        if (contenulangueids.isEmpty()) {
            throw new RuntimeException("Aucun dictionnaire trouvé pour l'ID de la langue : " + langueId);
        }

        List<CoursDetailDto> results = new ArrayList<>();

        List<Object> strategies = strategyManager.getStrategiesForCoursDetails(coursDetails);
        List<Integer> selectedContenuLangueIds = new ArrayList<Integer>();
        List<Integer> selectedcontenuLangueIds = new ArrayList<Integer>();

        for (int i = 0; i < coursDetails.size(); i++) {

            ICoursDetail coursDetail = coursDetails.get(i);

            int selectedContenuLangueId = coursDetails.get(i).getId_contenuLangue();
            Object strategy = strategies.get(i);

            if (strategy instanceof IChoisirPhraseJusteService) {
                CoursDetailDto result = ((IChoisirPhraseJusteService) strategy).generateCoursDetail(coursDetail,
                        contenuLangues, selectedContenuLangueId);
                results.add(result);
            }

            else if (strategy instanceof IPhraseJusteEnLangueService) {
                CoursDetailDto result = ((IPhraseJusteEnLangueService) strategy)
                        .generateChoisirPhraseEnLange(coursDetail, contenuLangues, selectedContenuLangueId);
                results.add(result);
            }

            else if (strategy instanceof IComparaisonVocale) {
                CoursDetailDto result = ((IComparaisonVocale) strategy).generateCoursDetail(coursDetail,
                        selectedContenuLangueId);
                results.add(result);
            }

            else if (strategy instanceof IMotsEtImageService) {

                if (selectedcontenuLangueIds.size() <= 4) {
                    selectedcontenuLangueIds.add(selectedContenuLangueId);
                }

                if (selectedcontenuLangueIds.size() == 4) {

                    List<ContenuLangue> contenuLangue = contenuLangueRepository
                            .findAllByContenuLangueIdIn(selectedcontenuLangueIds);

                    CoursDetailDto result = ((IMotsEtImageService) strategy).generateCoursMotsEtImages(contenuLangue,coursDetail, selectedContenuLangueIds);
                    results.add(result);
                    selectedcontenuLangueIds.clear();
                }
            }

            else if (strategy instanceof ISonEtMotService) {

                if (selectedContenuLangueIds.size() <= 4) {
                    selectedContenuLangueIds.add(selectedContenuLangueId);
                }

                System.out.println(selectedContenuLangueIds);

                if (selectedContenuLangueIds.size() == 4) {
                    List<ContenuLangue> contenuLangue = contenuLangueRepository
                            .findAllByContenuLangueIdIn(selectedContenuLangueIds);

                    CoursDetailDto result = ((ISonEtMotService) strategy).generateTemplatesSonEtMots(contenuLangue,
                            coursDetail, selectedContenuLangueIds);
                    results.add(result);

                    selectedContenuLangueIds.clear();
                }

            } else {

                CoursDetailDto result = ((IChoisirPhraseJusteService) strategy).generateCoursDetail(coursDetail,
                        contenuLangues, selectedContenuLangueId);
                results.add(result);
            }

        }

        return results;

    }

    @Override
    public List<CoursDetailTemplateDto> getAllCoursDetailTemplates() {

        List<CoursDetailTemplate> templates = coursDetailTemplateRepository.findAll();

        return templates.stream().map(template -> new CoursDetailTemplateDto(template)).collect(Collectors.toList());
    }


    public List<Integer> getIdContenuLangues(List<ICoursDetail> coursDetails) {
        return coursDetails.stream()
                .map(ICoursDetail::getId_contenuLangue)
                .collect(Collectors.toList());
    }


    @Override
    public boolean defineCours(DefinirCoursInputOuputDto input, String type) {

        try {
            // chapitre.setStatut("ENABLE");

            if (input.getId_TypeOfContent() == 0) {
                return false;
            }

            CoursDetailTemplate coursDetailTemplate = coursDetailTemplateRepository.findById(input.getId_template()) .orElseThrow(() -> new RuntimeException(
                "Aucun template trouvé pour l'ID de la langue : " + input.getId_template()));
                   
            if (type.toLowerCase().equals("cours")) {

                CoursDetail coursDetail = new CoursDetail();
                Cours cours = coursRepository.findById(input.getId_TypeOfContent()).orElseThrow(
                    () -> new RuntimeException(" Aucun cours trouvé pour l'ID de la langue : " + input.getId_TypeOfContent()));
             
                coursDetail.setId_contenuLangue(input.getId_contenuLangue());
                coursDetail.setRecompense(input.getRecompense());
                coursDetail.setCours(cours);
                coursDetail.setCoursDetailTemplate(coursDetailTemplate);

                coursDetailRepository.save(coursDetail);

                return true;
               
            }else if (type.toLowerCase().equals("cours")) {

                ExerciceChapitre exercice = new ExerciceChapitre();
                Chapitre chapitre = chapitreRepository.findById(input.getId_TypeOfContent()).orElseThrow(
                    () -> new RuntimeException("Aucun chapitre trouvé pour l'ID de la langue : " + input.getId_TypeOfContent()));

                exercice.setId_contenuLangue(input.getId_contenuLangue());
                exercice.setRecompense(input.getRecompense());
                exercice.setCoursDetailTemplate(coursDetailTemplate);
                exercice.setChapitre(chapitre);

                exerciceChapitreRepository.save(exercice);
                return true;

                
            }

            Evaluation evaluationDetail = new Evaluation();
            evaluationDetail.setRecompense(input.getRecompense());
            evaluationDetail.setCoursDetailTemplate(coursDetailTemplate);
            evaluationDetail.setContenuLangueId(input.getId_contenuLangue());
            evaluationDetail.setCours_id(input.getId_TypeOfContent());

            evaluationRepository.save(evaluationDetail);

            return true; 

        } catch (Exception e) {
            return false;
        }

    }

    
    @Override
    public NiveauInfo generateRecompense(String typeContent,int coursId, List<TemplateReponseDto> input, List<MultipartFile> files) throws Exception {

        int pointsObtenus = 0, j = 0, pointsTotal = 0;

        for (TemplateReponseDto reponse : input) {

            int templateId = reponse.getTemplateId();

            // Assume CoursDetailTemplate and strategyManager are properly injected
            CoursDetailTemplate coursDetailTemplate = coursDetailTemplateRepository.findById(templateId)
                    .orElseThrow(() -> new IllegalStateException("TemplateId not find"));
           
            Object result = strategyManager.getStrategyForLibelle(coursDetailTemplate.getCode());
            Object reponses = reponse.getReponses();

            if (result instanceof ChoisirPhraseJusteService || result instanceof PhraseJusteEnLangueService) {
                if (reponses instanceof Map) {
                    Map<String, Integer> mapReponses = (Map<String, Integer>) reponses;
                    pointsObtenus += processMapResponses(typeContent,coursId, templateId, mapReponses).get("pointsObtenus");
                    pointsTotal += processMapResponses(typeContent,coursId, templateId, mapReponses).get("pointsTotal");
                }
            } 
            
            else if (result instanceof MotsEtImageService || result instanceof SonEtMotService) {
                if (reponses instanceof List) {
                    List<Map<String, Object>> listReponses = (List<Map<String, Object>>) reponses;
                    pointsObtenus += processListResponses(typeContent,coursId, templateId, listReponses).get("pointsObtenus");
                    pointsTotal += processListResponses(typeContent,coursId, templateId, listReponses).get("pointsTotal");
                }
            } 
            
            else if (result instanceof ComparaisonVocale) {
                if (reponses instanceof Map && files != null) {
                    Map<String, Integer> mapReponses = (Map<String, Integer>) reponses;
                    pointsObtenus += processVocalComparison(typeContent,coursId, templateId, mapReponses,files.get(j)).get("pointsObtenus");
                    pointsTotal += processVocalComparison(typeContent,coursId, templateId, mapReponses, files.get(j)).get("pointsTotal");
                    j++;
                }
            }
        
        }

        logger.info("Request received with points total: ", pointsObtenus);

        Niveau niveaux = niveauRepository.findByPointMinLessThanEqualAndPointMaxGreaterThanEqual(pointsObtenus, pointsObtenus);
        Niveau niveau = niveauRepository.findByNom("DEBUTANT");
        
        if (typeContent.toLowerCase().equals("cours")){

            int progressionId = 1; // A remplacer par l'un des ids progression de l'apprenant dans la table Progression.

            if (pointsObtenus >= (pointsTotal*70) / 100) {
                updateProgressionCours(progressionId,coursId,pointsObtenus,true,false);
                return new NiveauInfo(pointsObtenus,true,false);

            } 
            else if(pointsObtenus <= (pointsTotal*70) / 100 && pointsObtenus != 0){
                updateProgressionCours(progressionId,coursId,pointsObtenus,false,true);
                return new NiveauInfo(pointsObtenus,false);
            }
            return new NiveauInfo(pointsObtenus,true);
        }

        return niveaux != null ? new NiveauInfo(niveaux.getNiveauId(), niveaux.getNom()) : new NiveauInfo(niveau.getNiveauId(), "DEBUTANT");

    }


    private void updateProgressionCours(int progressionId, int coursId, int sum,boolean estTermine,boolean aReprendre){
        Progression cours = progressionRepository.findById(progressionId).orElseThrow(() -> new IllegalArgumentException("Progression de cet utilisateur non trouvée"));

            List<ProgressionChapitre> progressionChapitres = cours.getProgressionChapitres();
            for (ProgressionChapitre progressionChapitre : progressionChapitres) {
                int progressionChapitreId = progressionChapitre.getProgressionChapitreId();

                List<ProgressionCours> progressionCours = progressionCoursRepository.findByProgressionChapitreProgressionChapitreId(progressionChapitreId);
                for (ProgressionCours progressionCours1 : progressionCours) {

                    if (progressionCours1.getCours().getCoursId() == coursId) {

                        progressionCours1.setPoints(sum);
                        progressionCours1.setEstTermine(estTermine);
                        progressionCours1.setaReprendre(aReprendre);
                        
                    }
                }
            }
    }


    private Map<String, Integer> processMapResponses(String typeContent,int coursId, int templateId, Map<String, Integer> map) throws Exception {

        Map<String, Integer> result = new HashMap<>();
        result.put("pointsObtenus", 0);
        logger.info("Request received with somme totale: {}", templateId);

        int contenuLangueId = map.get("contenuLangueId");
        int dictionaireId = map.get("dictionnaireId");
        int resultat = generateRecompense(coursId, templateId, contenuLangueId,typeContent);
        ContenuLangue contenuLangue = contenuLangueRepository.findById(contenuLangueId).orElseThrow(() -> new IllegalStateException("ContenuLangue Introuvable"));
        int correctDictionnaireId = contenuLangue.getDictionnaire().getDictionnaireId();

        if (dictionaireId == correctDictionnaireId) {
            result.put("pointsObtenus", resultat);
        }

        result.put("pointsTotal", resultat);
        return result;

    }


    private Map<String, Integer> processListResponses(String typeContent,int coursId, int templateId, List<Map<String, Object>> list) throws Exception {

        int sum = 0, count = 0, total = 0;
        Map<String, Integer> result = new HashMap<>();
        logger.info("Request received with somme totale: {}", templateId);

        for (Map<String, Object> item : list) {
            int contenuLangueId = (int) item.get("contenuLangueId");
            int dictionaireId = (int) item.get("dictionnaireId");
            int resultat = generateRecompense(coursId, templateId, contenuLangueId,typeContent);
            ContenuLangue contenuLangue = contenuLangueRepository.findById(contenuLangueId).orElseThrow(() -> new IllegalStateException("ContenuLangue Introuvable"));
            int correctDictionnaireId = contenuLangue.getDictionnaire().getDictionnaireId();
            if (dictionaireId == correctDictionnaireId) {
                sum += resultat;
            }
            count++;
            total += resultat;
        }
        
        result.put("pointsObtenus", sum / count);
        result.put("pointsTotal",total / count);

        return result;
    }


    private Map<String, Integer> processVocalComparison(String typeContent, int coursId, int templateId, Map<String, Integer> reponses, MultipartFile file) throws Exception {
        Map<String, Integer> result = new HashMap<>();
        result.put("pointsObtenus", 0);
        logger.info("Request received with somme totale: {}", templateId);

        int contenuLangueId = reponses.get("contenuLangueId");
        int resultat = generateRecompense(coursId, templateId, contenuLangueId,typeContent);
        ContenuLangue contenuLangue = contenuLangueRepository.findById(contenuLangueId).orElseThrow(() -> new IllegalStateException("ContenuLangue Introuvable"));
        String urlAudio = contenuLangue.getAudio();

        if (repeterService.compareVoice(file, urlAudio)) {
            result.put("pointsObtenus", resultat);
        }
        result.put("pointsTotal", resultat);
        return  result;
    }


    private int generateRecompense(int coursId, int templateId,int contenuLangueId,String type) {

        List<ICoursDetail> coursDetails = coursDetailFactoryService.getInfoAboutTemplateIdAndContentTypeId(coursId, templateId,type);
        
        ICoursDetail coursDetail = coursDetails.stream().filter(c -> c.getId_contenuLangue() == contenuLangueId).findFirst().orElseThrow(() -> new IllegalArgumentException("Probleme lors de la recuperation de la recompense"));

        return coursDetail.getRecompense();

    }

}
