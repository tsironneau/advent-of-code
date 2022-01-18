package y2021.day16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day16_1 {

    public static final int VERSION_SIZE = 3;
    public static final int TYPE_SIZE = 3;
    public static final int OPERATOR_LENGTH_TYPE_SIZE = 1;
    public static final int LITERAL_TYPE_ID = 4;

    public static void main(String[] args) {
        final IPacket packet = IPacket.decode(hexToBin(Input.INPUT));
        System.out.println(packet.versionSum());
    }

    static abstract class BasePacket implements IPacket {

        int version;
        int typeId;

        public BasePacket(final int version, final int typeId) {
            this.version = version;
            this.typeId = typeId;
        }

        public int version() {
            return version;
        }

        public int typeId() {
            return typeId;
        }

        @Override
        public String toString() {
            return "BasePacket{" +
                    "version=" + version +
                    ", typeId=" + typeId +
                    '}';
        }
    }

    static class LiteralPacket extends BasePacket {

        int binaryLength;
        private String binary;
        private BigInteger value;

        public LiteralPacket(final int version, final int typeId) {
            super(version, typeId);
        }

        public int versionSum() {
            return version;
        }

        @Override
        public int binaryLength() {
            return binaryLength;
        }

        public BigInteger value() {
            return value;
        }

        static LiteralPacket decode(String raw) {
            int currentIndex = 0;
            final String rawVersion = raw.substring(currentIndex, currentIndex += VERSION_SIZE);
            final String rawType = raw.substring(currentIndex, currentIndex += TYPE_SIZE);
            final int version = Integer.parseInt(rawVersion, 2);
            final int type = Integer.parseInt(rawType, 2);
            final LiteralPacket packet = new LiteralPacket(version, type);

            boolean isLastGroup = false;
            String binary = "";
            while (!isLastGroup) {
                isLastGroup = raw.charAt(currentIndex) == '0';
                binary += raw.substring(currentIndex + 1, currentIndex + 5);
                currentIndex += 5;
            }
            packet.binaryLength = currentIndex;
            packet.binary = binary;
            packet.value = new BigInteger(binary, 2);
            return packet;
        }

        @Override
        public String toString() {
            return "LiteralPacket{" +
                    "version=" + version +
                    ", typeId=" + typeId +
                    ", binaryLength=" + binaryLength +
                    ", binary='" + binary + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    static class OperatorPacket extends BasePacket {

        private final int lengthType;
        List<IPacket> subPackets = new ArrayList<>();

        public OperatorPacket(final int version, final int typeId, final int lengthType) {
            super(version, typeId);
            this.lengthType = lengthType;
        }

        public int versionSum() {
            return version + subPackets.stream().mapToInt(IPacket::versionSum).sum();
        }

        @Override
        public int binaryLength() {
            return VERSION_SIZE + TYPE_SIZE + OPERATOR_LENGTH_TYPE_SIZE +
                    (lengthType == 0 ? 15 : 11) +
                    subPackets.stream()
                            .mapToInt(IPacket::binaryLength)
                            .sum();
        }

        private static OperatorPacket decode(final String raw) {
            int currentIndex = 0;
            final String rawVersion = raw.substring(currentIndex, currentIndex += VERSION_SIZE);
            final String rawType = raw.substring(currentIndex, currentIndex += TYPE_SIZE);
            final int version = Integer.parseInt(rawVersion, 2);
            final int type = Integer.parseInt(rawType, 2);


            final String rawLengthType = raw.substring(currentIndex, ++currentIndex);
            final int lengthType = Integer.parseInt(rawLengthType, 2);
            final OperatorPacket packet = new OperatorPacket(version, type, lengthType);

            final boolean bySize = lengthType == 0;
            int lengthSize = bySize ? 15 : 11;
            final String rawLength = raw.substring(currentIndex, currentIndex += lengthSize);
            final int length = Integer.parseInt(rawLength, 2);
            if (bySize) {
                final String rawSubpackets = raw.substring(currentIndex, currentIndex += length);
                int readSize = 0;
                while (readSize < length) {
                    IPacket subPacket = IPacket.decode(rawSubpackets.substring(readSize));
                    packet.subPackets.add(subPacket);
                    readSize += subPacket.binaryLength();
                }
            } else {
                for (int i = 0; i < length; i++) {
                    final IPacket subPacket = IPacket.decode(raw.substring(currentIndex));
                    packet.subPackets.add(subPacket);
                    currentIndex += subPacket.binaryLength();
                }
            }

            return packet;
        }

        @Override
        public String toString() {
            return "OperatorPacket{" +
                    "version=" + version +
                    ", typeId=" + typeId +
                    ", lengthType=" + lengthType +
                    ", subPackets=" + String.join("\n", subPackets.stream().map(IPacket::toString).toList()) +
                    '}';
        }
    }

    static String hexToBin(String hex) {
        final String res = new BigInteger(hex, 16).toString(2);
        String formatPad = "%" + (hex.length() * 4) + "s";
        return String.format(formatPad, res).replace(" ", "0");
    }

    interface IPacket {

        static IPacket decode(String raw) {
            final String rawType = raw.substring(VERSION_SIZE, VERSION_SIZE + TYPE_SIZE);
            final int type = Integer.parseInt(rawType, 2);
            if (type == LITERAL_TYPE_ID) {
                return LiteralPacket.decode(raw);
            } else {
                return OperatorPacket.decode(raw);
            }
        }

        int version();

        int versionSum();

        int binaryLength();
    }
}
