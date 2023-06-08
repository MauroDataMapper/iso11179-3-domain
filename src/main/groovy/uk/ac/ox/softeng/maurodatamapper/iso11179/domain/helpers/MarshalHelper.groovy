package uk.ac.ox.softeng.maurodatamapper.iso11179.domain.helpers

import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBException
import jakarta.xml.bind.Marshaller
import uk.ac.ox.softeng.maurodatamapper.iso11179.domain.MetadataBundle

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
