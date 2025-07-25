/*
 * Copyright 2020-2025 University of Oxford and NHS England
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
package org.maurodata.iso11179.domain.helpers

import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBException
import jakarta.xml.bind.Marshaller
import org.maurodata.iso11179.domain.MetadataBundle

class MarshalHelper {

    static String marshallMetadataBundleToString(MetadataBundle metadataBundle, boolean prettyPrint = true) {
        Marshaller marshaller = createMarshaller(prettyPrint)
        StringWriter stringWriter = new StringWriter()
        try {
            marshaller.marshal(metadataBundle, stringWriter)
        } catch (JAXBException e) {
            e.printStackTrace()
        }
        return stringWriter.toString()
    }

    static void marshallMetadataBundleToFile(MetadataBundle metadataBundle, File file, boolean prettyPrint = true) {
        try {

            Marshaller marshaller = createMarshaller(prettyPrint)

            marshaller.marshal(metadataBundle, file)

        } catch (JAXBException e) {
            e.printStackTrace()
        }

    }


    private static Marshaller createMarshaller(boolean prettyPrint = true) {
        JAXBContext context = JAXBContext.newInstance(MetadataBundle.class)
        Marshaller marshaller = context.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "https://www.iso.org/11179/-3/2012 iso11179-3_message.xsd")

        if(prettyPrint) {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE)
        }
        return marshaller
    }

}
