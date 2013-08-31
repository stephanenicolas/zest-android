VM=6a5d9245-b751-47aa-b38d-989c5f1a9cfb

#find mac of vm
#http://stackoverflow.com/questions/10991771/sed-to-insert-colon-in-a-mac-address
# Update arp table
for i in {1..254}; do ping -c 1 192.168.56.$i 2&>1; done

MAC=`VBoxManage showvminfo "$VM" | grep MAC | grep Host | awk -F ":" '{print $3}' | cut -c 2-13`
#echo "MAC is $MAC"

MAC=`echo $MAC | sed -e 's/\([0-9A-Fa-f]\{2\}\)/\1:/g' -e 's/\(.*\):$/\1/' | tr '[:upper:]' '[:lower:]'`
#echo "MAC is $MAC"

# Find IP: substitute vname-mac-addr with your vm's mac address in ':' notation
IP=`arp -a | sed "s/ \(.\):/ 0\1:/" | sed "s/:\(.\):/:0\1:/g"|sed "s/:\(.\):/:0\1:/g"|sed "s/:\(.\)$/:0\1/"|grep $MAC`
#echo "IP is $IP"

IP=`echo $IP | cut -d "(" -f2 | cut -d ")" -f1`
echo $IP

