package uk.ac.ox.softeng.maurodatamapper.iso11179.test

import jakarta.xml.bind.JAXBException
import org.junit.jupiter.api.Test
import uk.ac.ox.softeng.maurodatamapper.iso11179.domain.MetadataBundle
import uk.ac.ox.softeng.maurodatamapper.iso11179.domain.RegistrySpecification
import uk.ac.ox.softeng.maurodatamapper.iso11179.domain.helpers.MarshalHelper

class DomainTest {


    @Test
    void SimpleEmptyDomain() {

        MetadataBundle bundle = new MetadataBundle().tap {
            it.sourceRegistry = new RegistrySpecification().tap {
                it.name = "Mauro Data Mapper"
                it.webAddress = ""
                it.standard = ""
                it.conformanceLevel = ""
                it.characterRepertoire = ""
                it.referenceDocumentIdentifierForm = ""
            }
        }

        String bundleString = ""
        try {
            bundleString = MarshalHelper.marshallMetadataBundleToString(bundle)
        } catch (JAXBException e) {
            e.printStackTrace()
        }

        String expected = """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<metadata_bundle xsi:schemaLocation="https://www.iso.org/11179/-3/2012 iso11179-3_message.xsd" xmlns="https://www.iso.org/11179/-3/2012" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <source_registry>
        <name>Mauro Data Mapper</name>
        <web_address></web_address>
        <standard></standard>
        <conformance_level></conformance_level>
        <character_repertoire></character_repertoire>
        <reference_document_identifier_form></reference_document_identifier_form>
    </source_registry>
</metadata_bundle>
"""

        assert bundleString.equals(expected)


    }


}
