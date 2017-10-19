package io.rscale.training.attendeeviewer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AttendeeController {

	private CompanyClient companyClient;
	
	@Autowired
	public AttendeeController(CompanyClient companyClient) {
		this.companyClient = companyClient;
	}
	
    @RequestMapping("/")
    public String attendees(@RequestParam(value="page", required=false, defaultValue= "0") int page, Model model) {
    	List<Attendee> attendees = new ArrayList<>();
    	attendees.add(createAttendee());
        model.addAttribute("attendees", attendees);
        return "list";
    }

    @RequestMapping("/{attendeeId}")
    public String attendee(@PathVariable(value="attendeeId") String attendeeId, Model model) {
        Attendee attendee = createAttendee();
    	model.addAttribute("attendee", attendee);
    	if ( ! StringUtils.isEmpty(attendee.getCompanyGuid()) ) {
    		model.addAttribute("company", companyClient.getCompany(attendee.getCompanyGuid()));
    	}
        return "detail";
    }

	private Attendee createAttendee() {
		Attendee attendee = new Attendee();
        attendee.setCompanyName("0f69d9dd-3ed8-4954-90d9-eae379a30f97	");
        attendee.setFirstName("Sebastian");
        attendee.setLastName("Krome");
        attendee.setUuid("sebastiank");
		return attendee;
	}
    
}