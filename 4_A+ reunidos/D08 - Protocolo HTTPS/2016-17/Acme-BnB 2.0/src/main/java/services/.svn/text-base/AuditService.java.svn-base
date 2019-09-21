
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Audit;
import domain.Auditor;
import domain.Property;
import repositories.AuditRepository;

@Service
@Transactional
public class AuditService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AuditRepository auditRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private AuditorService auditorService;

	// Constructors-------------------------------------------------------
	public AuditService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	// Other business methods -----------------------------------------------

	public Collection<Audit> findNoDraftsByPropertyId(int propertyId) {
		Collection<Audit> result;

		result = this.auditRepository.findNoDraftsByPropertyId(propertyId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Audit> findByPropertyAndAuditor(int propertyId, int auditorId) {
		Collection<Audit> result;

		result = this.auditRepository.findByPropertyAndAuditor(propertyId, auditorId);
		Assert.notNull(result);

		return result;
	}

	Collection<Audit> findByDraftPropertyAndAuditor(int propertyId, int auditorId) {
		Collection<Audit> result;

		result = this.auditRepository.findByDraftPropertyAndAuditor(propertyId, auditorId);
		Assert.notNull(result);

		return result;
	}

	public Audit create(int propertyId) {
		Audit a = new Audit();
		a.setProperty(propertyService.findOne(propertyId));
		a.setAuditor(auditorService.findByPrincipal());
		Collection<String> attachments = new HashSet<String>();
		a.setAttachments(attachments);
		a.setText("");
		a.setMoment(Calendar.getInstance().getTime());
		a.setDraft(true);
		return a;
	}

	public Audit findOne(Integer id) {

		return auditRepository.findOne(id);
	}

	public Audit save(Audit audit) {

		Assert.notNull(audit);
		Assert.isTrue(audit.isDraft());
		Auditor auditor = audit.getAuditor();
		Assert.isTrue(auditor.equals(auditorService.findByPrincipal()));
		Property p = audit.getProperty();
		Assert.isTrue(!propertyService.findByAuditorAudits(auditor.getId()).contains(p.getId()));
		// Borrar las audits borradores de esa propiedad
		Collection<Audit> drafts = findByDraftPropertyAndAuditor(audit.getProperty().getId(),
				audit.getAuditor().getId());
		for (Audit a : drafts) {
			delete(a);
		}
		audit.setDraft(false);
		Audit saved = auditRepository.save(audit);
		// Actualizar colección de las relaciones
		// Auditor
		Collection<Audit> audits = auditor.getAudits();
		audits.add(saved);
		auditor.setAudits(audits);
		auditorService.update(auditor);
		// Property
		audits = p.getAudits();
		audits.add(saved);
		p.setAudits(audits);
		propertyService.save(p);

		return saved;

	}

	public Audit saveAsDraft(Audit audit) {
		Assert.notNull(audit);
		Auditor auditor = audit.getAuditor();
		Assert.isTrue(auditor.equals(auditorService.findByPrincipal()));
		Property p = audit.getProperty();
		Assert.isTrue(!propertyService.findByAuditorAudits(auditor.getId()).contains(p.getId()));
		audit.setDraft(true);
		Audit saved = auditRepository.saveAndFlush(audit);
		// Actualizar colección de las relaciones
		// Auditor
		Collection<Audit> audits = auditor.getAudits();
		audits.add(saved);
		auditor.setAudits(audits);
		auditorService.update(auditor);
		// Property
		audits = p.getAudits();
		audits.add(saved);
		p.setAudits(audits);
		propertyService.save(p);

		return saved;

	}

	public void delete(Audit audit) {
		Assert.notNull(audit);
		Auditor auditor = audit.getAuditor();
		Assert.isTrue(auditor.equals(auditorService.findByPrincipal()));
		Property p = audit.getProperty();
		// Actualizar colección de las relaciones
		// Auditor
		Collection<Audit> audits = auditor.getAudits();
		audits.remove(audit);
		auditor.setAudits(audits);
		auditorService.update(auditor);
		// Property
		audits = p.getAudits();
		audits.remove(audit);
		p.setAudits(audits);
		propertyService.save(p);
		auditRepository.delete(audit);

	}

}
