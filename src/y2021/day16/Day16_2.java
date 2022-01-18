package y2021.day16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static y2021.day16.Input.INPUT;

public class Day16_2 {

    public static final int VERSION_SIZE = 3;
    public static final int TYPE_SIZE = 3;
    public static final int OPERATOR_LENGTH_TYPE_SIZE = 1;
    public static final int LITERAL_TYPE_ID = 4;

    public static void main(String[] args) {
        final String line = INPUT;

        final String s = hexToBin(line);

        final String d2FE28 = hexToBin("D2FE28");
        final LiteralPacket literal = LiteralPacket.decode(d2FE28);
        assert literal.value.intValue() == 2021;

        check("C200B40A82", 3);
        check("04005AC33890", 54);
        check("880086C3E88112", 7);
        check("CE00C43D881120", 9);
        check("F600BC2D8F", 0);
        check("9C005AC2F8F0", 0);
        check("9C0141080250320F1802104A08", 1);

        final IPacket packet = IPacket.decode(s);
        System.out.println(packet.value());
    }

    private static void check(final String raw, final int expectedValue) {
        final String binOp_2 = hexToBin(raw);
        final IPacket op2 = IPacket.decode(binOp_2);
        final long value = op2.value();
        System.out.println(value + ", expected = " + expectedValue);
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

        public long value() {
            return value.longValue();
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

        @Override
        public long value() {

            return switch (typeId) {
                case 0 -> subPackets.stream().mapToLong(IPacket::value).sum();
                case 1 -> subPackets.stream().mapToLong(IPacket::value).reduce(1, (a, b) -> a * b);
                case 2 -> subPackets.stream().mapToLong(IPacket::value).min().getAsLong();
                case 3 -> subPackets.stream().mapToLong(IPacket::value).max().getAsLong();
                case 5 -> subPackets.get(0).value() > subPackets.get(1).value() ? 1 : 0;
                case 6 -> subPackets.get(0).value() < subPackets.get(1).value() ? 1 : 0;
                case 7 -> subPackets.get(0).value() == subPackets.get(1).value() ? 1 : 0;
                default -> throw new IllegalArgumentException();
            };
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

        long value();
    }
}
