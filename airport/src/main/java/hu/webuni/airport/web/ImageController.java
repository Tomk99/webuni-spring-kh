package hu.webuni.airport.web;

import hu.webuni.airport.api.ImageControllerApi;
import hu.webuni.airport.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ImageController implements ImageControllerApi {

    private final NativeWebRequest nativeWebRequest;
    private final ImageRepository imageRepository;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(nativeWebRequest);
    }

    @Override
    public ResponseEntity<Resource> downloadImage(Long id) {
        return ResponseEntity.ok(new ByteArrayResource(imageRepository.findById(id).orElseThrow(NoSuchElementException::new).getData()));
    }
}
