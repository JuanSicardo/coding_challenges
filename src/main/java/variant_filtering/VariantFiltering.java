package variant_filtering;


import java.util.ArrayList;
import java.util.List;

public class VariantFiltering {

    public static List<String> filterVariants(List<String> variants, List<String> genomicRegions) {
        if (variants.isEmpty() || genomicRegions.isEmpty())
            return List.of();

        List<String> filteredVariants = new ArrayList<>();

        int i = 0;
        GenomicRegion genomicRegion = new GenomicRegion(genomicRegions.get(i));

        for (String variantString : variants) {
            Variant variant = new Variant(variantString);

            while (genomicRegion.chromosome < variant.chromosome || genomicRegion.getEndPosition() < variant.getPosition()) {
                if (i >= genomicRegions.size() - 1)
                    break;
                genomicRegion = new GenomicRegion(genomicRegions.get(++i));
            }

            if (variant.getChromosome() == genomicRegion.getChromosome() && variant.getPosition() >= genomicRegion.getStartPosition() && variant.getPosition() < genomicRegion.getEndPosition())
                filteredVariants.add(variant.toString());
        }

        return filteredVariants;
    }


    // Parse the following elements of a variant: number of the chromosome, position.
    private static class Variant {
        private final String encoding;
        private final int chromosome;
        private final int position;

        public Variant(String encoding) {
            this.encoding = encoding;
            String[] tokens = encoding.substring(3).split(":");
            this.chromosome = Integer.parseInt(tokens[0]);
            this.position = Integer.parseInt(tokens[1]);
        }

        @Override
        public String toString() {
            return encoding;
        }

        public int getChromosome() {
            return chromosome;
        }

        public int getPosition() {
            return position;
        }
    }


    // Parse the following elements of a genomic region: number of the chromosome, start and end.
    private static class GenomicRegion {

        private final String encoding;
        private final int chromosome;
        private final int startPosition;
        private final int endPosition;

        public GenomicRegion(String encoding) {
            this.encoding = encoding;
            String[] tokens = encoding.substring(3).split("[:\\-]");
            this.chromosome = Integer.parseInt(tokens[0]);
            this.startPosition = Integer.parseInt(tokens[1]);
            this.endPosition = Integer.parseInt(tokens[2]);
        }

        @Override
        public String toString() {
            return encoding;
        }

        public int getChromosome() {
            return chromosome;
        }

        public int getStartPosition() {
            return startPosition;
        }

        public int getEndPosition() {
            return endPosition;
        }
    }
}
