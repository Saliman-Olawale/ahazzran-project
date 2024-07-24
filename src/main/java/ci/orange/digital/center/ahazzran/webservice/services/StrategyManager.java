package ci.orange.digital.center.ahazzran.webservice.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;

@Component
public class StrategyManager {

    private final ApplicationContext context;

    public StrategyManager(ApplicationContext context) {
        this.context = context;
    } 

    public Object getStrategyForLibelle(String code) {

        if ("TEMP1".equals(code)) {
            return context.getBean(ChoisirPhraseJusteService.class);
        } 
        
        else if ("TEMP5".equals(code)) {
            return context.getBean(SonEtMotService.class);
        } 

        else if("TEMP4".equals(code)){
            return context.getBean(MotsEtImageService.class);
        }

        else if("TEMP2".equals(code)){
            return context.getBean(PhraseJusteEnLangueService.class);
        }

        else if("TEMP3".equals(code)){
            return context.getBean(ComparaisonVocale.class);
        }

        else {
            throw new IllegalArgumentException("Aucune stratégie définie pour le libellé : " + code);
        }
      
    }

    public List<Object> getStrategiesForCoursDetails(List<ICoursDetail> coursDetails) {
        return coursDetails.stream()
            .map(coursDetail -> getStrategyForLibelle(coursDetail.getCoursDetailTemplate().getCode()))
            .collect(Collectors.toList());
    }

}
