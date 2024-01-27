package variant_filtering;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VariantFilteringTest {

    @Test
    void ifNoVariantsAreProvidedReturnEmptyList() {
        List<String> variants = List.of();
        List<String> genomicRegions = List.of(
                "chr1:1-2",
                "chr10:20-22"
        );

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertTrue(result.isEmpty());
    }

    @Test
    void ifNoGenomicRegionsAreProvidedReturnEmptyList() {
        List<String> variants = List.of(
                "chr8:6:A>T",
                "chr9:2:C>G"
        );
        List<String> genomicRegions = List.of();

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertTrue(result.isEmpty());
    }

    @Test
    void ifVariantChromosomeIsLessThanGenomicRegionsChromosomeDontAddVariant() {
        List<String> variants = List.of(
                "chr1:6:A>D"
        );
        List<String> genomicRegions = List.of(
                "chr2:4-10"
        );

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertTrue(result.isEmpty());
    }

    @Test
    void ifVariantChromosomeIsMoreThanGenomicRegionsChromosomeDontAddVariant() {
        List<String> variants = List.of(
                "chr6:10:G>T"
        );
        List<String> genomicRegions = List.of(
                "chr5:5-22"
        );

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertTrue(result.isEmpty());
    }

    @Test
    void ifVariantChromosomeIsTheSameAsGenomicRegionButPositionIsBellowStartDontAddVariant() {
        List<String> variants = List.of(
                "chr10:12:G>A"
        );
        List<String> genomicRegions = List.of(
                "chr10:13-20"
        );

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertTrue(result.isEmpty());
    }

    @Test
    void ifVariantChromosomeIsTheSameAsGenomicRegionAndPositionIsTheSameAsStartAddVariant() {
        List<String> variants = List.of(
                "chr6:16:A>G"
        );
        List<String> genomicRegions = List.of(
                "chr6:16-22"
        );

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertEquals(result.getFirst(), "chr6:16:A>G");
    }

    @Test
    void ifVariantChromosomeIsTheSameAsGenomicRegionAndPositionIsOneBellowEndAddVariant() {
        List<String> variants = List.of(
                "chr9:15:T>G"
        );
        List<String> genomicRegions = List.of(
                "chr9:11-16"
        );

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertEquals(result.getFirst(), "chr9:15:T>G");
    }


    @Test
    void ifVariantChromosomeIsTheSameAsGenomicRegionAndPositionIsTheSameAsEndDontAddVariant() {
        List<String> variants = List.of(
                "chr5:22:A>T"
        );
        List<String> genomicRegions = List.of(
                "chr5:11-22"
        );

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertTrue(result.isEmpty());
    }

    @Test
    void filterVariousVariants() {
        List<String> variants = List.of(
                "chr1:5:G>C",
                "chr1:6:A>T",
                "chr1:8:A>C",
                "chr1:15:G>T",
                "chr8:10:C>G",
                "chr8:12:A>G",
                "chr19:3:G>A",
                "chr19:20:C>T",
                "chr19:22:T>A"
        );
        List<String> genomicRegions = List.of(
                "chr1:6-8",
                "chr19:1-2",
                "chr19:20-25"
        );
        List<String> expectedResult = List.of(
                "chr1:6:A>T",
                "chr19:20:C>T",
                "chr19:22:T>A"
        );

        List<String> result = VariantFiltering.filterVariants(variants, genomicRegions);
        assertEquals(expectedResult, result);
    }
}