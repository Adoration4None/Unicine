package co.edu.uniquindio.unicine.servicios;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicio {
    private final Cloudinary cloudinary;
    private final Map<String, String> config;

    public CloudinaryServicio() {
        config = new HashMap<>();
        config.put("cloud_name", "dheuspgiq");
        config.put("api_key", "193132624638958");
        config.put("api_secret", "HJDxMyvmThjkmtOLlofbQSMMGuw");

        cloudinary = new Cloudinary(config);
    }

    public Map subirImagen(File archivoImagen, String carpeta) throws Exception {
        return cloudinary.uploader().upload( archivoImagen, ObjectUtils.asMap("folder", String.format("unicine/%s", carpeta)) );
    }

    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy( idImagen, ObjectUtils.emptyMap() );
    }
}
