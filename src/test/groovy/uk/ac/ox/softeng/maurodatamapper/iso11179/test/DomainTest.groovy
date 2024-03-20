/*
 * Copyright 2020-2024 University of Oxford and NHS England
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
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
